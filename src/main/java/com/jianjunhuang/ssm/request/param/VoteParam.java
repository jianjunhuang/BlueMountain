package com.jianjunhuang.ssm.request.param;

public class VoteParam {
    private String userId;
    private String communityId;
    private boolean isAgree;

    public VoteParam() {
    }

    public VoteParam(String userId, String communityId, boolean isAgree) {
        this.userId = userId;
        this.communityId = communityId;
        this.isAgree = isAgree;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommunityId() {
        return communityId;
    }

    public void setCommunityId(String communityId) {
        this.communityId = communityId;
    }

    public boolean isAgree() {
        return isAgree;
    }

    public void setAgree(boolean agree) {
        isAgree = agree;
    }
}
