package com.jianjunhuang.ssm.request.param;

public class InsulationTemperatureParam {
    private String machineId;
    private float temperature;

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }
}
