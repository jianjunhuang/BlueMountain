package com.jianjunhuang.ssm.service;

import com.jianjunhuang.ssm.entity.Community;

import java.util.List;

public interface CommunityService {

    boolean addComment(String userId, String machineId, String title, String content);

    boolean vote(String communityId, String userId, int isAgree);

    List<Community> getAllCommunity(String machineId, String userId);

}
