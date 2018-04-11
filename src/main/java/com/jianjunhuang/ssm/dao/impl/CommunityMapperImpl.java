package com.jianjunhuang.ssm.dao.impl;

import com.jianjunhuang.ssm.base.mybatis.UUIDGenerator;
import com.jianjunhuang.ssm.dao.CommunityMapper;
import com.jianjunhuang.ssm.entity.Community;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class CommunityMapperImpl implements CommunityMapper {

    @Resource
    private UUIDGenerator uuidGenerator;

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<Community> getAllCommunity(String machineId, String userId) {
        return sqlSessionTemplate.selectList("CommunityMapper.getAllCommunity");
    }

    @Override
    public void addCommunity(Community community, String userId, String machineId) {
        Map map = new HashMap();
        community.setCommunityId(uuidGenerator.generateUUID());
        map.put("community", community);
        map.put("userId", userId);
        map.put("machineId", machineId);
        sqlSessionTemplate.insert("CommunityMapper.addCommunity", map);
    }

    @Override
    public void setAgree(String communityId, String userId, int isAgree) {
        Map map = new HashMap();
        map.put("communityId", communityId);
        map.put("userId", userId);
        map.put("isAgree", isAgree);
        sqlSessionTemplate.insert("CommunityMapper.setAgree", map);
    }
}
