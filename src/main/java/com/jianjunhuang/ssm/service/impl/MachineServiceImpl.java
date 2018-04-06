package com.jianjunhuang.ssm.service.impl;

import com.jianjunhuang.ssm.dao.MachineMapper;
import com.jianjunhuang.ssm.entity.Machine;
import com.jianjunhuang.ssm.service.MachineService;

import javax.annotation.Resource;

public class MachineServiceImpl implements MachineService {

    @Resource
    private MachineMapper machineMapper;


    @Override
    public void aaMachine(Machine machine) {
        machineMapper.addMachine(machine);
    }

    @Override
    public void updateMachine(Machine machine) {
        machineMapper.updateMachine(machine);
    }
}
