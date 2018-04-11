package com.jianjunhuang.ssm.service.impl;

import com.jianjunhuang.ssm.dao.MachineMapper;
import com.jianjunhuang.ssm.entity.Machine;
import com.jianjunhuang.ssm.service.MachineService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
public class MachineServiceImpl implements MachineService {

    @Resource
    private MachineMapper machineMapper;


    @Override
    @Transactional
    public boolean addMachine(Machine machine) {
        Machine m = machineMapper.getMachine(machine.getMachineId());
        if (null == m) {
            machineMapper.addMachine(machine);
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean updateMachine(Machine machine) {
        Machine m = machineMapper.getMachine(machine.getMachineId());
        if (null == m) {
            return false;
        }
        machineMapper.updateMachine(machine);
        return true;
    }
}
