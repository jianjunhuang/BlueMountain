package com.jianjunhuang.ssm.dao;

import com.jianjunhuang.ssm.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> getAllUser(@Param("machineId") String machineId);

    User getUser(@Param("userId") String userId);

    void addUser(@Param("user") User user);

    int updateUser(@Param("user") User user);

    User getUserByName(@Param("userName") String userName);
}
