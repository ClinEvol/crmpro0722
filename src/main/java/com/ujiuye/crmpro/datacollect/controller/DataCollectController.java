package com.ujiuye.crmpro.datacollect.controller;

import com.alibaba.fastjson.JSON;
import com.ujiuye.crmpro.datacollect.pojo.DataCollect;
import com.ujiuye.crmpro.datacollect.service.DataCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/dataCollect")
public class DataCollectController {
    @Autowired
    private DataCollectService dataCollectService;

    @RequestMapping("/listJSON")
    @ResponseBody
    public String listJSON(){
        List<DataCollect> list = dataCollectService.list();
        return JSON.toJSONString(list);
    }

    @RequestMapping("/toecharts")
    public String toecharts(){
        return "list-echarts";
    }
}
