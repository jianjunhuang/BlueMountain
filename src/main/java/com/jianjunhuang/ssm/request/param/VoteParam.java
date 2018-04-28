package com.jianjunhuang.ssm.request.param;

public class VoteParam {
    private String userId;
    private String communityId;
    private String isAgree;

    public VoteParam() {
    }

    public VoteParam(String userId, String communityId, String isAgree) {
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

    public String isAgree() {
        return isAgree;
    }

    public void setIsAgree(String agree) {
        isAgree = agree;
    }

    @Override
    public String toString() {
        return "userId=" + userId +
                "communityId=" + communityId +
                "isAgree=" + isAgree;
    }
}
