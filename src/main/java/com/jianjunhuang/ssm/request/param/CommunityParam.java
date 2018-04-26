package com.jianjunhuang.ssm.request.param;

import com.jianjunhuang.ssm.entity.Community;

import java.util.Date;

public class CommunityParam {
    private String userId;
    private String machineId;
    private String title;
    private String content;

    public CommunityParam() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
