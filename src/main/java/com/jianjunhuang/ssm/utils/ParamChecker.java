package com.jianjunhuang.ssm.utils;

import com.jianjunhuang.ssm.dto.Result;
import com.jianjunhuang.ssm.request.param.CommunityParam;
import com.jianjunhuang.ssm.request.param.IdParam;
import com.jianjunhuang.ssm.request.param.VoteParam;
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
        if (null == communityParam.getCommunityId() || "".equals(communityParam.getCommunityId())) {
            result.setStatus(Result.PARAMETER_LOST);
            result.setReason("communityId is null or equals ''");
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
        return result;
    }

}
