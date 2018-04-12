package com.jianjunhuang.ssm.controller;

import com.jianjunhuang.ssm.dao.CommunityMapper;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

@Controller
public class CommunityController {

    @Resource
    private CommunityMapper communityMapper;



}
