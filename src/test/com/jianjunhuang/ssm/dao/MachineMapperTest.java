package com.jianjunhuang.ssm.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:config/SpringMVC.xml"})
public class MachineMapperTest {

    @Resource
    private MachineMapper machineMapper;

    @Test
    public void getMachine() {
        System.out.println(machineMapper.getMachine("1234"));
    }

    @Test
    public void addMachine() {
    }

    @Test
    public void updateMachine() {
    }
}