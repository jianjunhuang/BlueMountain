package com.jianjunhuang.ssm.websocket;


import com.google.gson.Gson;
import com.jianjunhuang.ssm.entity.Machine;
import com.jianjunhuang.ssm.entity.User;
import com.jianjunhuang.ssm.service.MachineService;
import com.jianjunhuang.ssm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * just user for users
 */
@Component
public class CoffeeWebSocketHandler implements WebSocketHandler {

    //已建立连接的用户
    private static final Map<String, LinkedList<WebSocketSession>> users = new HashMap<>();

    @Resource
    private MachineService machineService;

    @Resource
    private UserService userService;

    //新连接建立的时候，被调用
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("connection established");
        String machineId = (String) session.getAttributes().get("machineId");
        String userId = (String) session.getAttributes().get("userId");
        LinkedList<WebSocketSession> list = users.get(machineId);
        if (null == list) {
            list = new LinkedList<>();
        }
        list.add(session);
        users.put(machineId, list);
        User user = userService.getUser(userId);
        if (null == user) {
            removeUser(machineId, userId);
            return;
        }
        if (user.getStatus() == User.OUTLINE) {
            user.setStatus(User.ONLINE);
            userService.updateUserStatus(user);
        }
        notifyUserToUpdateUsers(machineId);

    }

    //TODO 处理前端发送的文本信息
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        System.out.println(message.toString());
        String type = (String) session.getAttributes().get("type");
        String machineId = (String) session.getAttributes().get("machineId");
    }

    //传输错误时调用
    @Override
    public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
        System.out.println("Handle client transport error");
        removeSession(webSocketSession);
    }

    //连接关闭时调用
    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus closeStatus) throws Exception {
        System.out.println("Handle client close");
        removeSession(webSocketSession);
    }

    private static void removeSession(WebSocketSession webSocketSession) {
        String machineId = (String) webSocketSession.getAttributes().get("machineId");
        List<WebSocketSession> list = users.get(machineId);
        list.remove(webSocketSession);
        notifyUserToUpdateUsers(machineId);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }

    /**
     * 给所有在线用户发送消息
     *
     * @param message
     */
    public static void sendMessageToUsers(TextMessage message) {
        for (Map.Entry<String, LinkedList<WebSocketSession>> entry : users.entrySet()) {
            List<WebSocketSession> sessions = entry.getValue();
            for (int i = 0; i < sessions.size(); i++) {
                WebSocketSession session = sessions.get(i);
                sendMessage(message, session);
            }
        }
    }

    public static void sendMessageToUsersInSameGroup(TextMessage message, String machineId) {
        List<WebSocketSession> sessions = users.get(machineId);
        if (null == sessions || sessions.size() == 0) {
            System.out.println("not this machine : machineId=" + machineId);
            return;
        }
        for (int i = 0; i < sessions.size(); i++) {
            WebSocketSession session = sessions.get(i);
            sendMessage(message, session);
        }
    }

    /**
     * 给某个用户发送消息
     */
    public static void sendMessageToUser(String userId, String machineId, TextMessage message) {
        List<WebSocketSession> sessions = users.get(machineId);
        if (null == sessions || sessions.size() == 0) {
            return;
        }
        for (int i = 0; i < sessions.size(); i++) {
            WebSocketSession session = sessions.get(i);
            if (session == null) {
                continue;
            }
            String id = (String) session.getAttributes().get("userId");
            if (null != id && id.equals(userId)) {
                sendMessage(message, session);
                break;
            }
        }
    }

    private static void sendMessage(TextMessage textMessage, WebSocketSession session) {
        if (session.isOpen()) {
            try {
                session.sendMessage(textMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUser(String machineId, String userId) {
        LinkedList<WebSocketSession> list = users.get(machineId);
        Iterator<WebSocketSession> iterator = list.iterator();
        while (iterator.hasNext()) {
            WebSocketSession session = iterator.next();
            if (userId.equals(session.getAttributes().get("userId"))) {
                User user = userService.getUser(userId);
                user.setStatus(User.OUTLINE);
                userService.updateUserStatus(user);
                removeSession(session);
                break;
            }
        }
        notifyUsersToUpdateMachine(machineId);
    }

    public static void notifyUserToUpdateUsers(String machineId) {
        sendMessageToUsersInSameGroup(new TextMessage("{\"action\":0}"), machineId);
    }

    public static void notifyUsersToUpdateMachine(String machineId) {
        sendMessageToUsersInSameGroup(new TextMessage("{\"action\":1}"), machineId);
    }

    public static void notifyUserToGetCoffee(String machineId, String userId) {
        sendMessageToUser(userId, machineId, new TextMessage("{\"action\":4}"));
    }

//    public void notifyMachineToMakeCoffee(String machineId) {
//        sendMessageToMachine(new TextMessage("{\"action\":2}"), machineId);
//    }
//
//    public void notifyMachineToSetInsulationTemperature(String machine, int temperature) {
//        sendMessageToMachine(new TextMessage("{\"action\":3,\"temperature\":" + temperature + "}"), machine);
//    }
}
