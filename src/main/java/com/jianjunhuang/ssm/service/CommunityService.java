package com.jianjunhuang.ssm.service;

import com.jianjunhuang.ssm.entity.Community;

public interface CommunityService {

    void addComment(Community community);

    void vote(boolean isAgree);

}
