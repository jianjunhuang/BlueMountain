package com.jianjunhuang.ssm.utils;

import com.jianjunhuang.ssm.dto.Result;
import com.jianjunhuang.ssm.request.param.*;
import org.apache.ibatis.executor.ReuseExecutor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

@Component
public class ParamChecker {

    public Result checkIdParam(IdParam idParam) {
        Result result = new Result();
        if (null == idParam) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("参数缺失!");
            return result;
        }
        if (null == idParam.getUserId() || "".equals(idParam.getUserId())) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("userId 为空");
            return result;
        }
        if (null == idParam.getMachineId() || "".equals(idParam.getMachineId())) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("machineId 为空");
            return result;
        }
        return result;
    }

    public Result checkCommunityParam(CommunityParam communityParam) {
        Result result = new Result();
        if (null == communityParam) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("community parameter is null");
            return result;
        }
        if (null == communityParam.getUserId() || "".equals(communityParam.getUserId())) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("userId is null or equals ''");
            return result;
        }
        if (null == communityParam.getMachineId() || "".equals(communityParam.getMachineId())) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("communityId is null or equals ''");
            return result;
        }
        return result;
    }

    public Result checkVoteParam(VoteParam voteParam) {
        Result result = new Result();
        if (null == voteParam) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("vote parameter is null");
            return result;
        }
        if (null == voteParam.getUserId() || "".equals(voteParam.getUserId())) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("userId is null or equals ''");
            return result;
        }
        if (null == voteParam.getCommunityId() || "".equals(voteParam.getCommunityId())) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("communityId is null or equals ''");
            return result;
        }
        if (voteParam.isAgree() == null || voteParam.isAgree().equals("0")) {
            result.setStatus(Result.PARAMETER_ERR);
            result.setReason("please make a choice");
            return result;
        }
        return result;
    }

    public Result checkInsulationParam(InsulationTemperatureParam param) {
        Result result = new Result();
        if (null == param) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("insulation temperature param is null");
            return result;
        }
        if (null == param.getMachineId() || "".equals(param.getMachineId())) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("machine id is null or equals ''");
            return result;
        }

        return result;
    }

    public Result checkLoginParam(LoginParam loginParam) {
        Result result = new Result();
        if (null == loginParam) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("user name and user password is null");
            return result;
        }
        if (null == loginParam.getUserName() || "".equals(loginParam.getUserName())) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("user name is null or equals ''");
            return result;
        }
        return result;
    }

    public Result checkUpdateUserNameParam(UpdateUserNameParam param) {
        Result result = new Result();
        if (null == param) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("param is null");
            return result;
        }
        if (null == param.getUserId() || "".equals(param.getUserId())) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("userId is null or equals ''");
            return result;
        }
        if (null == param.getUserName() || "".equals(param.getUserName())) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("userName is null or equals ''");
            return result;
        }
        return result;
    }

    public Result checkUpdateUserCupSizeParam(UpdateUserCupSizeParam param) {
        Result result = new Result();
        if (null == param) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("param is null");
            return result;
        }
        if (null == param.getUserId() || "".equals(param.getUserId())) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("userId is null or equals ''");
            return result;
        }
        return result;
    }
}

