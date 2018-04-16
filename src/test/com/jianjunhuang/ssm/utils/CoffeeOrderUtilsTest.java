package com.jianjunhuang.ssm.utils;

import com.jianjunhuang.ssm.entity.Machine;
import com.jianjunhuang.ssm.entity.User;
import org.junit.Test;

import javax.annotation.Resource;
import javax.crypto.Mac;

import java.util.*;

import static org.junit.Assert.*;

public class CoffeeOrderUtilsTest {

    @Resource
    private CoffeeOrderUtils coffeeOrderUtils;

    @Test
    public void getUsers() {

    }

    @Test
    public void containMachine() {
    }

    @Test
    public void applyCoffee() {
    }

    @Test
    public void gotCoffee() {
    }

    @Test
    public void notifyCoffee() {
        if (coffeeOrderUtils == null) {
            coffeeOrderUtils = new CoffeeOrderUtils();
        }
        Machine machine = new Machine();
        machine.setMachineId("1234");
        machine.setConnected(false);
        machine.setLevel(600);
        machine.setStatus(0);
        machine.setTemperature(1000);
        System.out.println(machine.hashCode());

        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setUserId("123456");
        user.setStatus(0);
        userList.add(user);
        coffeeOrderUtils.applyCoffee(machine.getMachineId(), userList);

        Map<String, LinkedList<User>> map = CoffeeOrderUtils.getCoffeeOrderMap();
        Set<String> set = map.keySet();
        for (Object m : set) {
            System.out.println(m);
        }

        System.out.println(coffeeOrderUtils.containMachine("1234"));
    }
}