package com.jianjunhuang.ssm.service;

import com.jianjunhuang.ssm.entity.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    void updateUserStatus(User user);

    List<User> getAllUser();

    User getUser(String userId);

    void reservationCoffee(String userId);

    void connectedToCoffee(String machineId, User user);

}
