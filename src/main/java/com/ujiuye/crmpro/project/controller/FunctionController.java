package com.ujiuye.crmpro.project.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.project.pojo.Analysis;
import com.ujiuye.crmpro.project.pojo.Function;
import com.ujiuye.crmpro.project.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/function")
public class FunctionController {
    @Autowired
    private FunctionService functionService;

    //列表页
    @RequestMapping("/list")
    public String list(
            Model model,
            @RequestParam(value = "type",required = false,defaultValue = "0") int type,
            @RequestParam(value = "keyword",required = false,defaultValue = "") String keyword,
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
            @RequestParam(value = "size",required = false,defaultValue = "10") int size
    ){
        //设置分页
        PageHelper.startPage(pageNum,size);
        //查询
        List<Function> list=functionService.list(type,keyword);
        //创建分页对象
        PageInfo<Function> page=new PageInfo<>(list);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "list-function";
    }


    @RequestMapping("/tosave")
    public String tosave(){
        return "save-function";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(Function function){
        try {
            int result=functionService.save(function);
            if (result>0){
                return "true";
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }


    @RequestMapping("/listByModuleId")
    @ResponseBody
    public String listByModuleId(int module_id){
        List<Function> list = functionService.list(module_id);
        return JSON.toJSONString(list);
    }
}
