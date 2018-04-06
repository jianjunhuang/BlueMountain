package com.jianjunhuang.ssm.service;

import com.jianjunhuang.ssm.entity.User;

import java.util.List;

public interface UserService {

    void addUser(User user,String machineId);

    void updateUserStatus(User user);

    List<User> getAllUser();

    User getUser(String userId);

    void reservationCoffee(String userId,String machineId);

    void connectedToCoffee(String machineId, User user);

}
