package com.jianjunhuang.ssm.service.impl;

import com.jianjunhuang.ssm.entity.Community;
import com.jianjunhuang.ssm.service.CommunityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Override
    @Transactional
    public void addComment(String userId, String machineId, Community community) {

    }

    @Override
    @Transactional
    public void vote(String userId, String machineId, boolean isAgree) {

    }
}
