package com.jianjunhuang.ssm.controller;

import com.jianjunhuang.ssm.dto.Result;
import com.jianjunhuang.ssm.service.MachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MachineController {

    @Resource
    private MachineService machineService;

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "machine/addMachine", method = RequestMethod.POST)
    @ResponseBody
    public Result<Map> addMachine(HttpServletRequest request, HttpServletResponse response, String machineId) {
        Map<Object, Object> resultMap = new HashMap<>();
        Result<Map> result = new Result<>();
        if (null != machineId && "".equals(machineId)) {
            result.setReason("machineId is null");
            result.setStatus(Result.PARAMETER_LOST);
            return result;
        }
        result.setStatus(Result.SUCCESS);
        return result;
    }

}
