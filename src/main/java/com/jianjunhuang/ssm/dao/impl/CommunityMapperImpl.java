package com.jianjunhuang.ssm.dao.impl;

import com.jianjunhuang.ssm.dao.CommunityMapper;
import com.jianjunhuang.ssm.entity.Community;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class CommunityMapperImpl implements CommunityMapper {
    @Override
    public List<Community> getAllCommunity(String machineId, String userId) {
        return null;
    }

    @Override
    public void addCommunity(Community community, String userId, String machineId) {

    }

    @Override
    public void setAgree(String communityId, String userId, int isAgree) {

    }
}
