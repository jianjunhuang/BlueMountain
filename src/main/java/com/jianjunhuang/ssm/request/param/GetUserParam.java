package com.jianjunhuang.ssm.request.param;

public class GetUserParam {

    private String userId;
    private String machineId;

    public GetUserParam() {
        super();
    }
    public GetUserParam(String userId, String machineId) {
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

    @Override
    public String toString() {
        return "userId = " + userId +
                "\nmachineId = " + machineId;
    }
}
