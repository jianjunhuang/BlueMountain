package com.jianjunhuang.ssm.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("User")
public class User {
    private String userId;
    private String name;
    private int status;
    private float favTemperature;
    private float cupSize;

    public User() {
        super();
    }

    public User(String userId, String name, int status, float favTemperature, float cupSize) {
        this.userId = userId;
        this.name = name;
        this.status = status;
        this.favTemperature = favTemperature;
        this.cupSize = cupSize;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getFavTemperature() {
        return favTemperature;
    }

    public void setFavTemperature(float favTemperature) {
        this.favTemperature = favTemperature;
    }

    public float getCupSize() {
        return cupSize;
    }

    public void setCupSize(float cupSize) {
        this.cupSize = cupSize;
    }

    @Override
    public String toString() {
        return "\n\nname = " + name +
                "\nuserId = " + userId +
                "\nstatus = " + status +
                "\nfav temp = " + favTemperature +
                "\ncup size = " + cupSize;
    }
}
