package com.jianjunhuang.ssm.service.impl;

import com.jianjunhuang.ssm.dao.MachineMapper;
import com.jianjunhuang.ssm.dao.UserMapper;
import com.jianjunhuang.ssm.entity.User;
import com.jianjunhuang.ssm.service.UserService;

import javax.annotation.Resource;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private MachineMapper machineMapper;

    @Override
    public void addUser(User user, String machineId) {
        userMapper.addUser(user, machineId);
    }

    @Override
    public void updateUserStatus(User user) {
        //todo
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public User getUser(String userId) {
        return null;
    }

    @Override
    public void reservationCoffee(String userId,String machineId) {
        //todo
    }

    @Override
    public void connectedToCoffee(String machineId, User user) {
        //1. 检查是否有此 machine
        //2. 检查是否有此用户
    }
}
