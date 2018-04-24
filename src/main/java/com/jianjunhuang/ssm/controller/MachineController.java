package com.jianjunhuang.ssm.controller;

import com.jianjunhuang.ssm.dto.Result;
import com.jianjunhuang.ssm.entity.Machine;
import com.jianjunhuang.ssm.request.param.IdParam;
import com.jianjunhuang.ssm.request.param.InsulationTemperatureParam;
import com.jianjunhuang.ssm.service.MachineService;
import com.jianjunhuang.ssm.utils.ParamChecker;
import com.jianjunhuang.ssm.websocket.CoffeeWebSocketHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class MachineController {

    @Resource
    private MachineService machineService;
    @Resource
    private ParamChecker paramChecker;
    @Resource
    private CoffeeWebSocketHandler handler;

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "machine/updateMachine", method = RequestMethod.POST)
    @ResponseBody
    public Result<Map> updateMachine(HttpServletRequest request, HttpServletResponse response, @RequestBody Machine machine) {
        Result<Map> result = new Result<>();
        if (null == machine || "".equals(machine.getMachineId())) {
            result.setReason("machineId is null");
            result.setStatus(Result.PARAMETER_LOST);
            return result;
        }
        if (!machineService.addMachine(machine)) {
            machineService.updateMachine(machine);
        }
        result.setStatus(Result.SUCCESS);
        handler.sendMessageToUsersInSameGroup(new TextMessage("{\"action\":1}"), machine.getMachineId());
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "machine/orderCoffee", method = RequestMethod.POST)
    @ResponseBody
    public Result orderCoffee(HttpServletRequest request, HttpServletResponse response, @RequestBody IdParam userParam) {
        Result result = paramChecker.checkIdParam(userParam);
        if (result.getStatus() != Result.SUCCESS) {
            return result;
        }
        Machine machine = machineService.getMachine(userParam.getMachineId());
        if (machine.getStatus() == Machine.STATUS_DISCONNECTED) {
            result.setReason("咖啡机断开连接");
            result.setStatus(Result.FAILED);
            return result;
        }
        if (machine.getStatus() != Machine.STATUS_MAKING_COFFEE) {
            handler.sendMessageToMachine(new TextMessage("{\"action\":2}"), userParam.getMachineId());
        }

        //TODO add to order list
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "machine/setInsulationTemperature", method = RequestMethod.POST)
    @ResponseBody
    public Result setInsulationTemperature(HttpServletRequest request, HttpServletResponse response, @RequestBody InsulationTemperatureParam param) {
        Result result = paramChecker.checkInsulationParam(param);
        if (result.getStatus() != Result.SUCCESS) {
            return result;
        }
        String json = "{\"action\":3,\"temperature\":" + param.getTemperature() + "}";
        handler.sendMessageToMachine(new TextMessage(json), param.getMachineId());
        return result;
    }

}
