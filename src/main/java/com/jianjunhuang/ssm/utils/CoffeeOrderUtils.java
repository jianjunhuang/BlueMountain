package com.jianjunhuang.ssm.utils;

import com.jianjunhuang.ssm.dao.MachineMapper;
import com.jianjunhuang.ssm.entity.Machine;
import com.jianjunhuang.ssm.entity.User;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CoffeeOrderUtils {

    private static Map<String, LinkedList<User>> coffeeOrderMap = new HashMap<>();
    private static Map<String, Machine> machineMap = new HashMap<>();

    @Resource
    private MachineMapper machineMapper;

    public LinkedList<User> getUsers(String machineId) {
        return coffeeOrderMap.get(machineId);
    }

    public boolean containMachine(String machineId) {
        Machine machine = new Machine();
        machine.setMachineId(machineId);
        return coffeeOrderMap.containsKey(machineId);
    }

    public void applyCoffee(String machineId, List<User> users) {
        if (null == machineId) {
            System.out.println("machineId is null");
            return;
        }
        if (null == users || users.size() == 0) {
            System.out.println("List<User> is null");
            return;
        }
        coffeeOrderMap.put(machineId, new LinkedList<>(users));
        //TODO notify
    }

    public void gotCoffee(String machineId, User user) {
        if (null == user) {
            return;
        }
        LinkedList<User> users = getUsers(machineId);
        for (int i = 0; i < users.size(); i++) {
            User bean = users.get(i);
            if (user.getUserId().equals(bean.getUserId())) {
                users.remove(i);
                return;
            }
        }
        users.add(user);
        //TODO notify
    }

    public void notifyCoffee(String machineId) {
        if (null == machineId || "".equals(machineId)) {
            return;
        }
//        LinkedList<User> user = getUsers()
    }

    public static Map<String, LinkedList<User>> getCoffeeOrderMap() {
        return coffeeOrderMap;
    }
}
