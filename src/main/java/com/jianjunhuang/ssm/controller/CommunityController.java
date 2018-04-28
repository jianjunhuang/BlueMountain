package com.jianjunhuang.ssm.controller;

import com.jianjunhuang.ssm.dao.CommunityMapper;
import com.jianjunhuang.ssm.dto.Result;
import com.jianjunhuang.ssm.entity.Community;
import com.jianjunhuang.ssm.request.param.CommunityParam;
import com.jianjunhuang.ssm.request.param.IdParam;
import com.jianjunhuang.ssm.request.param.VoteParam;
import com.jianjunhuang.ssm.service.CommunityService;
import com.jianjunhuang.ssm.utils.ParamChecker;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CommunityController {

    @Resource
    private CommunityService communityService;
    @Resource
    private ParamChecker paramChecker;

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "community/addAVote", method = RequestMethod.POST)
    @ResponseBody
    public Result addAVote(HttpServletRequest request, HttpServletResponse response, @RequestBody CommunityParam communityParam) {
        Result result = paramChecker.checkCommunityParam(communityParam);
        if (result.getStatus() != Result.SUCCESS) {
            return result;
        }
        communityService.addComment(communityParam.getUserId(),
                communityParam.getMachineId(),
                communityParam.getTitle(),
                communityParam.getContent());
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "community/vote", method = RequestMethod.POST)
    @ResponseBody
    public Result vote(HttpServletRequest request, HttpServletResponse response, @RequestBody VoteParam voteParam) {
        System.out.println(voteParam);
        Result result = paramChecker.checkVoteParam(voteParam);
        if (result.getStatus() != Result.SUCCESS) {
            return result;
        }
        communityService.vote(voteParam.getCommunityId(), voteParam.getUserId(), Integer.parseInt(voteParam.isAgree()));
        return result;
    }

    @RequestMapping(produces = "application/json;charset=UTF-8", value = "community/get", method = RequestMethod.POST)
    @ResponseBody
    public Result getCommunity(HttpServletRequest request, HttpServletResponse response, @RequestBody IdParam idParam) {
        Result result = paramChecker.checkIdParam(idParam);
        if (result.getStatus() != Result.SUCCESS) {
            return result;
        }
        List<Community> communities = communityService.getAllCommunity(idParam.getMachineId(), idParam.getUserId());
        result.setData(communities);
        return result;
    }


}
