package com.jianjunhuang.ssm.dao.impl;

import com.jianjunhuang.ssm.dao.UserMapper;
import com.jianjunhuang.ssm.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class UserMapperImpl implements UserMapper {
    @Override
    public List<User> getAllUser(String machineId) {
        return null;
    }

    @Override
    public User getUser(String machineId, String userId) {
        return null;
    }

    @Override
    public void addUser(User user, String machineId) {

    }

    @Override
    public int updateUser(User user) {
        return 0;
    }
}
