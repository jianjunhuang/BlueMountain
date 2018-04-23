package com.jianjunhuang.ssm.dao.impl;

import com.jianjunhuang.ssm.dao.MachineMapper;
import com.jianjunhuang.ssm.entity.Machine;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;

@Repository
public class MachineMapperImpl implements MachineMapper {

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    @Override
    public Machine getMachine(String machineId) {
        return sqlSessionTemplate.selectOne("getMachine", machineId);
    }

    @Override
    public void addMachine(Machine machine) {
        machine.setLastUpdate(new Date());
        sqlSessionTemplate.insert("addMachine", machine);
    }

    @Override
    public void updateMachine(Machine machine) {
        machine.setLastUpdate(new Date());
        sqlSessionTemplate.update("updateMachine", machine);
    }
}
