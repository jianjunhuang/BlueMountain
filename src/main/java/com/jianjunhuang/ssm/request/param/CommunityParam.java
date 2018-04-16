package com.jianjunhuang.ssm.request.param;

import com.jianjunhuang.ssm.entity.Community;

import java.util.Date;

public class CommunityParam extends Community {
    private String userId;
    private String machineId;

    public CommunityParam() {
    }

    public CommunityParam(String communityId, String title, String content, Date date, int agreeNum, int disagreeNum, int isAgree, String userId, String machineId) {
        super(communityId, title, content, date, agreeNum, disagreeNum, isAgree);
        this.userId = userId;
        this.machineId = machineId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }
}
