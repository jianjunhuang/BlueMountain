package com.jianjunhuang.ssm.controller;

import com.jianjunhuang.ssm.dto.Result;
import com.jianjunhuang.ssm.entity.Machine;
import com.jianjunhuang.ssm.entity.User;
import com.jianjunhuang.ssm.request.param.IdParam;
import com.jianjunhuang.ssm.request.param.InsulationTemperatureParam;
import com.jianjunhuang.ssm.serversocket.EspServerSocket;
import com.jianjunhuang.ssm.service.MachineService;
import com.jianjunhuang.ssm.service.UserService;
import com.jianjunhuang.ssm.utils.CoffeeOrderUtils;
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
    private UserService userService;
    @Resource
    private ParamChecker paramChecker;
    @Resource
    private CoffeeOrderUtils coffeeOrderUtils;

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
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "machine/orderCoffee", method = RequestMethod.POST)
    @ResponseBody
    public Result orderCoffee(HttpServletRequest request, HttpServletResponse response, @RequestBody IdParam userParam) {
        Result result = paramChecker.checkIdParam(userParam);
        if (result.getStatus() != Result.SUCCESS) {
            return result;
        }
        User user = userService.getUser(userParam.getUserId());
        if (null == user) {
            result.setStatus(Result.FAILED);
            result.setReason("user not found");
            return result;
        }

        Machine machine = machineService.getMachine(userParam.getMachineId());
        if (machine.getStatus() == Machine.STATUS_DISCONNECTED) {
            result.setReason("咖啡机断开连接");
            result.setStatus(Result.FAILED);
            return result;
        }
        if (coffeeOrderUtils.addUser(userParam.getMachineId(), userParam.getUserId())) {
            user.setStatus(User.WAITING);
            userService.updateUserStatus(user);
            CoffeeWebSocketHandler.notifyUserToUpdateUsers(userParam.getMachineId());
        } else {
            result.setStatus(Result.FAILED);
            result.setReason("已预约咖啡");
        }
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "machine/setInsulationTemperature", method = RequestMethod.POST)
    @ResponseBody
    public Result setInsulationTemperature(HttpServletRequest request, HttpServletResponse response, @RequestBody InsulationTemperatureParam param) {
        Result result = paramChecker.checkInsulationParam(param);
        if (result.getStatus() != Result.SUCCESS) {
            return result;
        }
        Machine machine = machineService.getMachine(param.getMachineId());
        if (null == machine) {
            result.setStatus(Result.PARAMETER_ERR);
            result.setReason("machine mot found");
            return result;
        }
        machine.setInsulation(param.getTemperature());
        machineService.updateMachine(machine);
        EspServerSocket.notifyMachineToSetInsulationTemperature(machine.getMachineId(), (int) param.getTemperature());
        result.setData(machine);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "machine/getMachine", method = RequestMethod.POST)
    @ResponseBody
    public Result<Machine> getMachine(HttpServletResponse response, HttpServletRequest request, @RequestBody IdParam idParam) {
        Result<Machine> result = new Result<>();
        String machineId = idParam.getMachineId();
        if (null == machineId || "".equals(machineId)) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("lost parameter machineId");
            return result;
        }

        if (result.getStatus() != Result.SUCCESS) {
            return result;
        }
        System.out.println("machineId = " + machineId);
        Machine machine = machineService.getMachine(machineId);
        if (null == machine) {
            result.setStatus(Result.FAILED);
            result.setReason("machine not found");
            return result;
        }
        result.setData(machine);
        return result;
    }

}
