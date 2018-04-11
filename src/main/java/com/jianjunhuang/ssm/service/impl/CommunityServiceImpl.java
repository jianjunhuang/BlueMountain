package com.jianjunhuang.ssm.service.impl;

import com.jianjunhuang.ssm.base.mybatis.UUIDGenerator;
import com.jianjunhuang.ssm.dao.CommunityMapper;
import com.jianjunhuang.ssm.entity.Community;
import com.jianjunhuang.ssm.service.CommunityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Resource
    private CommunityMapper communityMapper;

    @Override
    @Transactional
    public boolean addComment(String userId, String machineId, Community community) {
        communityMapper.addCommunity(community, userId, machineId);
        return true;
    }

    @Override
    @Transactional
    public boolean vote(String userId, String machineId, boolean isAgree) {
        communityMapper.setAgree(userId, machineId, isAgree);
        return true;
    }

    @Override
    @Transactional
    public List<Community> getAllCommunity(String machineId, String userId) {
        return communityMapper.getAllCommunity(machineId, userId);
    }
}
