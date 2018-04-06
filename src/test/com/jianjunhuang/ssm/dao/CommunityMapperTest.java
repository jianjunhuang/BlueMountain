package com.jianjunhuang.ssm.dao;

import com.jianjunhuang.ssm.entity.Community;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml"})
public class CommunityMapperTest {

    @Resource
    private CommunityMapper communityMapper;

    @Test
    public void getAllCommunity() {
        System.out.println(communityMapper.getAllCommunity("1234","123456"));
    }

    @Test
    public void addCommunity() {
        Community community = new Community();
        community.setTitle("title");
        community.setCommunityId("123456789");
        community.setContent("content");
        community.setDate(new Date());
        community.setAgreeNum(0);
        community.setDisagreeNum(0);
        community.setIsAgree(0);

        communityMapper.addCommunity(community, "123456", "1234");
    }

    @Test
    public void setAgree() {
        communityMapper.setAgree("123456789","123456",0);
    }
}