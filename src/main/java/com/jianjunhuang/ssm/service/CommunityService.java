package com.jianjunhuang.ssm.service;

import com.jianjunhuang.ssm.entity.Community;

public interface CommunityService {

    void addComment(String userId, String machineId, Community community);

    void vote(String userId, String machineId, boolean isAgree);

}
