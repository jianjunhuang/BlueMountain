package com.jianjunhuang.ssm.service.impl;

import com.jianjunhuang.ssm.dao.MachineMapper;
import com.jianjunhuang.ssm.dao.UserMapper;
import com.jianjunhuang.ssm.entity.User;
import com.jianjunhuang.ssm.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private MachineMapper machineMapper;

    @Override
    @Transactional
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    @Transactional
    public void updateUserStatus(User user) {
        userMapper.updateUser(user);
    }

    @Override
    @Transactional
    public List<User> getAllUser(String machineId) {

        return userMapper.getAllUser(machineId);
    }

    @Override
    @Transactional
    public User getUser(String userId) {
        return userMapper.getUser(userId);
    }

    @Override
    @Transactional
    public void reservationCoffee(String userId, String machineId) {
        //todo
    }

    @Override
    @Transactional
    public void connectedToCoffee(String machineId, User user) {
        //1. 检查是否有此 machine
        //2. 检查是否有此用户
    }

    @Override
    public User getUserByName(String userName) {
        return userMapper.getUserByName(userName);
    }
}
