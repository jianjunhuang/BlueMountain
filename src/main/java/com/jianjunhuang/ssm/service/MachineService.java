package com.jianjunhuang.ssm.service;

import com.jianjunhuang.ssm.entity.Machine;

public interface MachineService {

    boolean addMachine(Machine machine);

    boolean updateMachine(Machine machine);

    Machine getMachine(String machineId);

}
