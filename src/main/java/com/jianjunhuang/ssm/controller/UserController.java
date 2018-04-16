package com.jianjunhuang.ssm.controller;

import com.jianjunhuang.ssm.dao.MachineMapper;
import com.jianjunhuang.ssm.dao.UserMapper;
import com.jianjunhuang.ssm.dto.Result;
import com.jianjunhuang.ssm.entity.Machine;
import com.jianjunhuang.ssm.entity.User;
import com.jianjunhuang.ssm.request.param.IdParam;
import com.jianjunhuang.ssm.utils.ParamChecker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class UserController {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MachineMapper machineMapper;

    @Resource
    private ParamChecker paramChecker;

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "user/connectedOrUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Result<Map> connectedOrUpdate(HttpServletRequest request, HttpServletResponse response, @RequestBody User user) {
        Result result = new Result();
        if (null == user) {
            result.setStatus(Result.PARAMETER_ERR);
            result.setReason("请检查传入的参数是否正确");
            return result;
        }
        String machineId = user.getMachineId();
        if (null == machineId || "".equals(machineId)) {
            result.setStatus(Result.MACHINE_NOT_FOUND);
            result.setReason("输入的 machineId 错误");
            return result;
        }
        Machine machine = machineMapper.getMachine(machineId);
        if (null == machine) {
            result.setStatus(Result.MACHINE_NOT_FOUND);
            result.setReason("没有找到该咖啡机，请先添加咖啡机！");
            return result;
        }
        if (!machine.isConnected()) {
            result.setStatus(Result.FAILED);
            result.setReason("咖啡机断线了，请重新联网！");
            return result;
        }
        if (null == userMapper.getUser(user.getUserId(), user.getMachineId())) {
            userMapper.addUser(user);
        } else {
            userMapper.updateUser(user);
        }
        result.setStatus(Result.SUCCESS);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "user/getUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result<User> getUserInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody IdParam idParam) {
        Result result = new Result();
        User user = userMapper.getUser(idParam.getMachineId(), idParam.getUserId());
        result.setStatus(Result.SUCCESS);
        result.setData(user);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "user/disconnected", method = RequestMethod.POST)
    @ResponseBody
    public Result disconnected(HttpServletResponse response, HttpServletRequest request, @RequestBody IdParam idParam) {
        Result result = paramChecker.checkIdParam(idParam);
        User user = userMapper.getUser(idParam.getMachineId(), idParam.getUserId());
        if (null == user) {
            result.setStatus(Result.FAILED);
            result.setReason("no this user");
            return result;
        }
        System.out.println(user);
        user.setStatus(-1);
        userMapper.updateUser(user);
        return result;
    }
}
