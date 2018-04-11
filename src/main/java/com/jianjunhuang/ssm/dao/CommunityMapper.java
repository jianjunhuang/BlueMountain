package com.jianjunhuang.ssm.dao;

import com.jianjunhuang.ssm.entity.Community;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommunityMapper {

    List<Community> getAllCommunity(@Param("machineId") String machineId, @Param("userId") String userId);

    void addCommunity(@Param("community") Community community, @Param("userId") String userId, @Param("machineId") String machineId);

    void setAgree(@Param("communityId") String communityId, @Param("userId") String userId, @Param("isAgree") boolean isAgree);

}
