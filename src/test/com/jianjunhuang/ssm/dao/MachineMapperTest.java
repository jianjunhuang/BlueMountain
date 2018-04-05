package com.jianjunhuang.ssm.dao;

import com.jianjunhuang.ssm.entity.Machine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/applicationContext-dao.xml"})
public class MachineMapperTest {

    @Resource
    private MachineMapper machineMapper;

    @Test
    public void getMachine() {
        Machine machine = machineMapper.getMachine("1234");
        System.out.println(machine.toString());
    }

    @Test
    public void addMachine() {
        Machine machine = new Machine();
        machine.setConnected(true);
        machine.setLevel(1000);
        machine.setMachineId("1234");
        machine.setStatus(0);
        machine.setTemperature(35);
        machineMapper.addMachine(machine);
    }
}