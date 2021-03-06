package com.jianjunhuang.ssm.service;

import com.jianjunhuang.ssm.entity.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void updateUserStatus(User user);

    List<User> getAllUser(String machineId);

    User getUser(String userId);

    void reservationCoffee(String userId, String machineId);

    void connectedToCoffee(String machineId, User user);

    User getUserByName(String userName);

}
