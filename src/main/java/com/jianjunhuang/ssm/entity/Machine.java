package com.jianjunhuang.ssm.entity;

public class Machine {
    private String machineId;
    private int status;
    private float temperature;
    private int level;
    private boolean isConnected;

    public String getMachineId() {
        return machineId;
    }

    public void setMachineId(String machineId) {
        this.machineId = machineId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    @Override
    public String toString() {
        return "\n\nmachineId = " + machineId +
                "\nstatus = " + status +
                "\ntemperature = " + temperature +
                "\nlevel=" + level +
                "\nisConnected=" + isConnected;
    }
}
