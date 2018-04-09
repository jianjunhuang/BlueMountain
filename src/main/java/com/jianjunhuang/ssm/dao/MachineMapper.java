package com.jianjunhuang.ssm.dao;

import com.jianjunhuang.ssm.entity.Machine;

public interface MachineMapper {

    Machine getMachine(String machineId);

    void addMachine(Machine machine);

    void updateMachine(Machine machine);
}


