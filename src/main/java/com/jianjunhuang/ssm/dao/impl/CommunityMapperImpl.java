package com.jianjunhuang.ssm.dao.impl;

import com.jianjunhuang.ssm.dao.CommunityMapper;
import com.jianjunhuang.ssm.entity.Community;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class CommunityMapperImpl implements CommunityMapper {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public List<Community> getAllCommunity(String machineId, String userId) {
        return sqlSessionTemplate.selectList("CommunityMapper.getAllCommunity");
    }

    @Override
    public void addCommunity(Community community, String userId, String machineId) {

    }

    @Override
    public void setAgree(String communityId, String userId, int isAgree) {

    }
}
