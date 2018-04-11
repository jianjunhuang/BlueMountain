package com.jianjunhuang.ssm.dao.impl;

import com.jianjunhuang.ssm.base.mybatis.UUIDGenerator;
import com.jianjunhuang.ssm.dao.UserMapper;
import com.jianjunhuang.ssm.entity.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserMapperImpl implements UserMapper {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;
    @Resource
    private UUIDGenerator uuidGenerator;

    @Override
    public List<User> getAllUser(String machineId) {
        return sqlSessionTemplate.selectList("UserMapper.getAllUser", machineId);
    }

    @Override
    public User getUser(String machineId, String userId) {
        Map map = new HashMap();
        map.put("machineId", machineId);
        map.put("userId", userId);
        return sqlSessionTemplate.selectOne("UserMapper.getUser", map);
    }

    @Override
    public void addUser(User user, String machineId) {
        user.setUserId(uuidGenerator.generateUUID());
        Map map = new HashMap();
        map.put("user", user);
        map.put("machineId", machineId);
        sqlSessionTemplate.insert("UserMapper.addUser", map);
    }

    @Override
    public int updateUser(User user) {
        sqlSessionTemplate.update("UserMapper.updateUser", user);
        return 0;
    }
}
