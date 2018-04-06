package com.jianjunhuang.ssm.dao;

import com.jianjunhuang.ssm.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    List<User> getAllUser(@Param("machineId") String machineId);

    User getUser(@Param("machineId") String machineId, @Param("userId") String userId);

    int addUser(@Param("user") User user, @Param("machineId") String machineId);

    int updateUser(@Param("user") User user);
}
