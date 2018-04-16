package com.jianjunhuang.ssm.controller;

import com.jianjunhuang.ssm.dao.CommunityMapper;
import com.jianjunhuang.ssm.dto.Result;
import com.jianjunhuang.ssm.entity.Community;
import com.jianjunhuang.ssm.request.param.CommunityParam;
import com.jianjunhuang.ssm.request.param.VoteParam;
import com.jianjunhuang.ssm.utils.ParamChecker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CommunityController {

    @Resource
    private CommunityMapper communityMapper;
    @Resource
    private ParamChecker paramChecker;

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "community/addAVote", method = RequestMethod.POST)
    @ResponseBody
    public Result addAVote(HttpServletRequest request, HttpServletResponse response, @RequestBody CommunityParam communityParam) {
        Result result = paramChecker.checkCommunityParam(communityParam);
        if (result.getStatus() != Result.SUCCESS) {
            return result;
        }
        communityMapper.addCommunity(communityParam, communityParam.getUserId(), communityParam.getMachineId());
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "community/vote", method = RequestMethod.POST)
    @ResponseBody
    public Result vote(HttpServletRequest request, HttpServletResponse response, @RequestBody VoteParam voteParam) {
        Result result = paramChecker.checkVoteParam(voteParam);
        if (result.getStatus() != Result.SUCCESS) {
            return result;
        }
        communityMapper.setAgree(voteParam.getCommunityId(), voteParam.getUserId(), voteParam.isAgree());
        return result;
    }



}
