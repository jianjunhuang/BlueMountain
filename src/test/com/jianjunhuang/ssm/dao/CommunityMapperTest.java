package com.jianjunhuang.ssm.dao;

import com.jianjunhuang.ssm.entity.Community;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommunityMapperTest {

    @Test
    public void getAllCommunity() {
    }

    @Test
    public void addCommunity() {
        Community community = new Community();
        community.setTitle("title");
        community.setCommunityId("123456789");
        community.setContent("content");
        community.setDate();
    }

    @Test
    public void setAgree() {
    }
}