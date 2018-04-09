package com.jianjunhuang;

import com.jianjunhuang.ssm.dao.UserMapper;
import com.jianjunhuang.ssm.entity.User;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:config/Mybatis.xml"})
public class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    public void getAllUser() {
        System.out.println(userMapper.getAllUser("1234"));
    }

    @Test
    public void getUser() {
        System.out.println(userMapper.getUser("1234","123456"));
    }

    @Test
    public void addUser() {
        User user = new User();
        user.setName("haha");
        user.setUserId("45678");
        user.setCupSize(550);
        user.setFavTemperature(55);
        user.setStatus(0);
        userMapper.addUser(user,"1234");
    }
}
