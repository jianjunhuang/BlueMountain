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
    public boolean addComment(String userId, String machineId, String title, String content) {
        Community community = new Community();
        community.setTitle(title);
        community.setContent(content);
        communityMapper.addCommunity(community, userId, machineId);
        return true;
    }

    @Override
    @Transactional
    public boolean vote(String communityId, String userId, int isAgree) {
        communityMapper.setAgree(communityId, userId, isAgree);
        return true;
    }

    @Override
    @Transactional
    public List<Community> getAllCommunity(String machineId, String userId) {
        return communityMapper.getAllCommunity(machineId, userId);
    }
}
