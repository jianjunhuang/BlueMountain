package com.jianjunhuang.ssm.service.impl;

import com.jianjunhuang.ssm.entity.Community;
import com.jianjunhuang.ssm.service.CommunityService;
import org.springframework.stereotype.Service;

@Service
public class CommunityServiceImpl implements CommunityService {

    @Override
    public void addComment(String userId, String machineId, Community community) {

    }

    @Override
    public void vote(String userId, String machineId, boolean isAgree) {

    }
}
