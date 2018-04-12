package com.jianjunhuang.ssm.controller;

import com.jianjunhuang.ssm.dto.Result;
import com.jianjunhuang.ssm.entity.Machine;
import com.jianjunhuang.ssm.service.MachineService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class MachineController {

    @Resource
    private MachineService machineService;

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "machine/updateMachine", method = RequestMethod.POST)
    @ResponseBody
    public Result<Map> updateMachine(HttpServletRequest request, HttpServletResponse response,@RequestBody Machine machine) {
        Result<Map> result = new Result<>();
        if (null == machine || "".equals(machine.getMachineId())) {
            result.setReason("machineId is null");
            result.setStatus(Result.PARAMETER_LOST);
            return result;
        }
        System.out.println(machine);
        if (machineService.addMachine(machine)) {
        } else {
            machineService.updateMachine(machine);
        }
        result.setStatus(Result.SUCCESS);
        return result;
    }

}
