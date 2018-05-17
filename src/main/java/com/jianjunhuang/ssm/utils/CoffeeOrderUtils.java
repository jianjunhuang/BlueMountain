package com.jianjunhuang.ssm.utils;

import com.jianjunhuang.ssm.dao.MachineMapper;
import com.jianjunhuang.ssm.dao.UserMapper;
import com.jianjunhuang.ssm.entity.Machine;
import com.jianjunhuang.ssm.entity.User;
import com.jianjunhuang.ssm.serversocket.EspServerSocket;
import com.jianjunhuang.ssm.service.MachineService;
import com.jianjunhuang.ssm.service.UserService;
import com.jianjunhuang.ssm.websocket.CoffeeWebSocketHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;

import javax.annotation.Resource;
import java.util.*;

import static java.lang.Thread.sleep;

/*
控制所有等待的用户

 */
@Component
public class CoffeeOrderUtils {

    // machineId : usersId
    private static Map<String, LinkedList<String>> orderMap = new HashMap<>();

    @Resource
    private MachineService machineService;

    @Resource
    private UserService userService;

    public String getUser(String machineId, String userId) {
        LinkedList<String> users = orderMap.get(machineId);
        if (null == users) {
            return null;
        }
        for (String str : users) {
            if (str.equals(userId)) {
                return str;
            }
        }
        return null;
    }

    public void removeUser() {
    }


    public boolean addUser(String machineId, String userId) {
        LinkedList<String> users = orderMap.get(machineId);
//        if (null != users) {
//            if (null != getUser(machineId, userId)) {
//                return false;
//            }
//            users.add(userId);
//        } else {
//            users = new LinkedList<>();
//            users.add(userId);
//            orderMap.put(machineId, users);
//        }
        EspServerSocket.notifyMachineToMakeCoffee(machineId);
        return true;
    }

    public void notifyUserToGetCoffee(String machineId) {
        System.out.println("<<<<<<<<<<<<<<<<<<<<notify user to get coffee-1>>>>>>>>>>>>>>>>>");

        new Thread(() -> {
            LinkedList<String> users = orderMap.get(machineId);
            if (null == users) {
                return;
            }
            Machine machine = machineService.getMachine(machineId);
//        int level = 1800;
//        machine.setLevel(level);

            Iterator<String> iterator = users.iterator();
            while (iterator.hasNext()) {
                String userId = iterator.next();
                User user = userService.getUser(userId);
                if (null == user) {
                    iterator.remove();
                    continue;
                }
//            if (user.getCupSize() < level) {
                System.out.println("<<<<<<<<<<<<<<<<<<<<notify user to get coffee-2>>>>>>>>>>>>>>>>>");
                iterator.remove();
                CoffeeWebSocketHandler.notifyUserToGetCoffee(machineId, userId);
//                level = level - (int) user.getCupSize();
//            }
//                wait 5 minute
                try {
                    sleep(5 * 1000 * 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//        if (users.size() > 0) {
//            EspServerSocket.notifyMachineToMakeCoffee(machineId);
//        }
        }).start();

    }
}
