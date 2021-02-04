package com.ujiuye.crmpro.forumpost.controller;

import com.ujiuye.crmpro.forumpost.pojo.Evaluate;
import com.ujiuye.crmpro.forumpost.pojo.EvaluateExample;
import com.ujiuye.crmpro.forumpost.pojo.Forumpost;
import com.ujiuye.crmpro.forumpost.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/evaluate")
public class EvaluateController {
    @Autowired
    private EvaluateService evaluateService;


    @RequestMapping("/save")
    @ResponseBody
    public String save(Evaluate evaluate){
        try {
            int result = evaluateService.save(evaluate);
            if(result>0){
                return "true";
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }
}
