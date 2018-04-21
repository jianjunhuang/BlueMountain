package com.jianjunhuang.ssm.websocket;


import com.jianjunhuang.ssm.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.*;

@Component
public class CoffeeWebSocketHandler implements WebSocketHandler {

    //已建立连接的用户
    private static final Map<String, LinkedList<WebSocketSession>> users = new HashMap<>();
    private static final List<WebSocketSession> machines = new ArrayList<>();

    //新连接建立的时候，被调用
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        String type = (String) session.getAttributes().get("type");
        String machineId = (String) session.getAttributes().get("machineId");
        if (!type.equals("machine")) {
            String userId = (String) session.getAttributes().get("userId");
            LinkedList<WebSocketSession> list = users.get(machineId);
            if (null == list) {
                list = new LinkedList<>();
            }
            list.add(session);
            users.put(machineId, list);
            //TODO
            sendMessageToUsersInSameGroup(new TextMessage(""), machineId);
        } else {
            machines.add(session);
            //TODO
            sendMessageToUsersInSameGroup(new TextMessage(""), machineId);
        }

    }

    //TODO 处理前端发送的文本信息
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String username = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        // 获取提交过来的消息详情
        System.out.println("收到用户 " + username + "的消息:" + message.toString());
        System.out.println(session.getAttributes());
        //回复一条信息，
        session.sendMessage(new TextMessage("reply msg:" + message.getPayload()));
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

    private void removeSession(WebSocketSession webSocketSession) {
        String type = (String) webSocketSession.getAttributes().get("type");
        String machineId = (String) webSocketSession.getAttributes().get("machineId");
        if (type.equals("machine")) {
            machines.remove(webSocketSession);
            //TODO
            sendMessageToUsersInSameGroup(new TextMessage(""),machineId);
        } else {
            List<WebSocketSession> list = users.get(machineId);
            list.remove(webSocketSession);
            //TODO
            sendMessageToUsersInSameGroup(new TextMessage(""),machineId);
        }
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
    public void sendMessageToUsers(TextMessage message) {
        for (Map.Entry<String, LinkedList<WebSocketSession>> entry : users.entrySet()) {
            List<WebSocketSession> sessions = entry.getValue();
            for (int i = 0; i < sessions.size(); i++) {
                WebSocketSession session = sessions.get(i);
                sendMessage(message, session);
            }
        }
    }

    public void sendMessageToUsersInSameGroup(TextMessage message, String machineId) {
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
    public void sendMessageToUser(String userId, String machineId, TextMessage message) {
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

    /**
     * 给所有机器发送信息
     *
     * @param textMessage
     */
    public void sendMessageToMachines(TextMessage textMessage) {
        for (int i = 0; i < machines.size(); i++) {
            WebSocketSession session = machines.get(i);
            sendMessage(textMessage, session);
        }
    }

    /**
     * 给某个机器发送信息
     *
     * @param textMessage
     * @param machineId
     */
    public void sendMessageToMachine(TextMessage textMessage, String machineId) {
        for (int i = 0; i < machines.size(); i++) {
            WebSocketSession session = machines.get(i);
            String id = (String) session.getAttributes().get("machineId");
            if (machineId.equals(id)) {
                sendMessage(textMessage, session);
                break;
            }
        }
    }

    private void sendMessage(TextMessage textMessage, WebSocketSession session) {
        if (session.isOpen()) {
            try {
                session.sendMessage(textMessage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
