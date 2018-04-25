package com.jianjunhuang.ssm.utils;

import com.jianjunhuang.ssm.dao.MachineMapper;
import com.jianjunhuang.ssm.dao.UserMapper;
import com.jianjunhuang.ssm.entity.Machine;
import com.jianjunhuang.ssm.entity.User;
import com.jianjunhuang.ssm.websocket.CoffeeWebSocketHandler;
import org.springframework.web.socket.WebSocketHandler;

import javax.annotation.Resource;
import java.util.*;

public class CoffeeOrderUtils {

    private static Map<String, HashSet<User>> coffeeOrderMap = new HashMap<>();
    private static Map<String, Machine> machineMap = new HashMap<>();

    @Resource
    private MachineMapper machineMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private CoffeeWebSocketHandler handler;

    public HashSet<User> getUsers(String machineId) {
        return coffeeOrderMap.get(machineId);
    }

    public User getUser(String machineId, String userId) {
        HashSet<User> users = getUsers(machineId);
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        User user = userMapper.getUser(userId);
        users.add(user);
        return user;
    }

    public Machine getMachine(String machineId) {
        Machine machine = machineMap.get(machineId);
        if (null == machine) {
            machine = machineMapper.getMachine(machineId);
        }
        return machine;
    }

    public boolean containMachine(String machineId) {
        Machine machine = new Machine();
        machine.setMachineId(machineId);
        return coffeeOrderMap.containsKey(machineId);
    }

    public void applyCoffee(String machineId, List<User> users) {
//        if (null == machineId) {
//            System.out.println("machineId is null");
//            return;
//        }
//        if (null == users || users.size() == 0) {
//            System.out.println("List<User> is null");
//            return;
//        }
//        coffeeOrderMap.put(machineId, new LinkedList<>(users));
//        //TODO notify
    }

    public void gotCoffee(String machineId, User user) {
        if (null == user) {
            return;
        }
        HashSet<User> users = getUsers(machineId);
//        for (int i = 0; i < users.size(); i++) {
//            User bean = users.
//            if (user.getUserId().equals(bean.getUserId())) {
//                //TODO change status
//                bean.setStatus(-1);
//                return;
//            }
//        }
//        users.add(user);
//        //TODO notify
    }

    public void notifyCoffee(String machineId) {
        if (null == machineId || "".equals(machineId)) {
            return;
        }
//        LinkedList<User> user = getUsers()
    }

    public static Map<String, HashSet<User>> getCoffeeOrderMap() {
        return coffeeOrderMap;
    }
}
