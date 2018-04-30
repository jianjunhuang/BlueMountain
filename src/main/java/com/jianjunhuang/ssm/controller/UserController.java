package com.jianjunhuang.ssm.controller;

import com.jianjunhuang.ssm.dto.Result;
import com.jianjunhuang.ssm.entity.Machine;
import com.jianjunhuang.ssm.entity.User;
import com.jianjunhuang.ssm.request.param.IdParam;
import com.jianjunhuang.ssm.request.param.LoginParam;
import com.jianjunhuang.ssm.request.param.UpdateUserCupSizeParam;
import com.jianjunhuang.ssm.request.param.UpdateUserNameParam;
import com.jianjunhuang.ssm.service.MachineService;
import com.jianjunhuang.ssm.service.UserService;
import com.jianjunhuang.ssm.utils.ParamChecker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private MachineService machineService;

    @Resource
    private ParamChecker paramChecker;

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "user/update", method = RequestMethod.POST)
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
        Machine machine = machineService.getMachine(machineId);
        if (null == machine) {
            result.setStatus(Result.MACHINE_NOT_FOUND);
            result.setReason("没有找到该咖啡机，请先添加咖啡机！");
            return result;
        }
        if (machine.getStatus() == Machine.STATUS_DISCONNECTED) {
            result.setStatus(Result.FAILED);
            result.setReason("咖啡机断线了，请重新联网！");
            return result;
        }
        if (null == userService.getUser(user.getUserId())) {
            result.setStatus(Result.PARAMETER_ERR);
            result.setReason("请先注册用户");
            return result;
        }
        userService.updateUserStatus(user);
        result.setStatus(Result.SUCCESS);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "user/getUserInfo", method = RequestMethod.POST)
    @ResponseBody
    public Result<User> getUserInfo(HttpServletRequest request, HttpServletResponse response, @RequestBody String userId) {
        Result result = new Result();
        User user = userService.getUser(userId);
        result.setStatus(Result.SUCCESS);
        result.setData(user);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "user/disconnected", method = RequestMethod.POST)
    @ResponseBody
    public Result disconnected(HttpServletResponse response, HttpServletRequest request, @RequestBody IdParam idParam) {
        Result result = paramChecker.checkIdParam(idParam);
        User user = userService.getUser(idParam.getUserId());
        if (null == user) {
            result.setStatus(Result.FAILED);
            result.setReason("no this user");
            return result;
        }
        if (!idParam.getMachineId().equals(user.getMachineId())) {
            result.setStatus(Result.PARAMETER_ERR);
            result.setReason("用户并没有连接该咖啡机");
            return result;
        }
        System.out.println(user);
        user.setMachineId("");
        user.setStatus(User.OUTLINE);
        userService.updateUserStatus(user);
        result.setData(user);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "user/connect", method = RequestMethod.POST)
    @ResponseBody
    public Result connect(HttpServletRequest request, HttpServletResponse response, @RequestBody IdParam idParam) {
        Result<Machine> result = paramChecker.checkIdParam(idParam);
        if (result.getStatus() != Result.SUCCESS) {
            return result;
        }
        User user = userService.getUser(idParam.getUserId());
        if (null == user) {
            result.setStatus(Result.USER_NOT_FOUND);
            result.setReason("user not found");
            return result;
        }
        Machine machine = machineService.getMachine(idParam.getMachineId());
        if (null == machine) {
            result.setStatus(Result.MACHINE_NOT_FOUND);
            result.setReason("machine not found");
            return result;
        }
        user.setMachineId(machine.getMachineId());
        result.setData(machine);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "user/getAllUsers", method = RequestMethod.POST)
    @ResponseBody
    public Result<List<User>> getAllUsers(HttpServletRequest request, HttpServletResponse response, @RequestBody IdParam idParam) {
        Result<List<User>> result = paramChecker.checkIdParam(idParam);
        if (result.getStatus() != Result.SUCCESS) {
            return result;
        }
        List<User> list = userService.getAllUser(idParam.getMachineId());
        result.setData(list);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "user/register", method = RequestMethod.POST)
    @ResponseBody
    public Result register(HttpServletResponse response, HttpServletRequest request, @RequestBody User user) {
        Result result = new Result();
        if (null == user) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("parameter lost");
            return result;
        }
        System.out.println("register = " + user);
        String userName = user.getName();
        if (null == userName || "".equals(userName)) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("用户名不能为空");
            return result;
        }
        User userTemp = userService.getUserByName(userName);
        if (userTemp != null) {
            result.setStatus(Result.FAILED);
            result.setReason("用户名已被注册");
            return result;
        }
        if (null == user.getPwd() || "".equals(user.getPwd())) {
            result.setStatus(Result.PARAMETER_ERR);
            result.setReason("请输入正确的8位密码");
            return result;
        }
        userService.addUser(user);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "user/checkUserName", method = RequestMethod.POST)
    @ResponseBody
    public Result checkUserName(HttpServletResponse response, HttpServletRequest request, @RequestBody String userName) {
        Result result = new Result();
        if (null == userName || "".equals(userName)) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("用户名不能为空");
            return result;
        }
        User user = userService.getUserByName(userName);
        if (user != null) {
            result.setStatus(Result.FAILED);
            result.setReason("用户名已被占用");
            return result;
        }
        result.setReason("用户名能被正常使用");
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "user/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(HttpServletResponse response, HttpServletRequest request, @RequestBody LoginParam loginParam) {
        Result result = paramChecker.checkLoginParam(loginParam);
        User user = userService.getUserByName(loginParam.getUserName());
        if (null == user) {
            result.setStatus(Result.FAILED);
            result.setReason("user is not exist");
            return result;
        }
        System.out.println("register = " + loginParam);
        if (!user.getPwd().equals(loginParam.getUserPwd())) {
            result.setStatus(Result.FAILED);
            result.setReason("password is wrong");
            return result;
        }
        result.setData(user);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "user/updateUserName", method = RequestMethod.POST)
    @ResponseBody
    public Result updateUserName(HttpServletRequest request, HttpServletResponse response, @RequestBody UpdateUserNameParam param) {
        Result result = paramChecker.checkUpdateUserNameParam(param);
        if (result.getStatus() != Result.SUCCESS) {
            return result;
        }
        User user = userService.getUser(param.getUserId());
        if (null == user) {
            result.setStatus(Result.PARAMETER_ERR);
            result.setReason("user not found");
            return result;
        }
        if (userService.getUserByName(param.getUserName()) != null) {
            result.setStatus(Result.FAILED);
            result.setReason("user name can not repeat");
            return result;
        }
        user.setName(param.getUserName());
        userService.updateUserStatus(user);
        result.setData(user);
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "user/updateUserCupSize", method = RequestMethod.POST)
    @ResponseBody
    public Result updateUserCupSize(HttpServletRequest request, HttpServletResponse response, @RequestBody UpdateUserCupSizeParam param) {
        Result result = paramChecker.checkUpdateUserCupSizeParam(param);
        if (result.getStatus() != Result.SUCCESS) {
            return result;
        }
        User user = userService.getUser(param.getUserId());
        if (null == user) {
            result.setStatus(Result.PARAMETER_ERR);
            result.setReason("user not found");
            return result;
        }
        user.setCupSize(param.getCupSize());
        userService.updateUserStatus(user);
        result.setData(user);
        return result;
    }

}
