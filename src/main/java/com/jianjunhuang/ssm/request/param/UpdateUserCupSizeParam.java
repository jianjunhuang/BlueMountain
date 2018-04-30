package com.jianjunhuang.ssm.request.param;

public class UpdateUserCupSizeParam {
    private String userId;
    private int cupSize;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getCupSize() {
        return cupSize;
    }

    public void setCupSize(int cupSize) {
        this.cupSize = cupSize;
    }
}
