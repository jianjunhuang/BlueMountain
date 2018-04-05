package com.jianjunhuang.ssm.dao;

import com.jianjunhuang.ssm.entity.Machine;
import org.apache.ibatis.annotations.Param;

public interface MachineMapper {

    Machine getMachine(String machineId);

    void addMachine(Machine machine);

}


