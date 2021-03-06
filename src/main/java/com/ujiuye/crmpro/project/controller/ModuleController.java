package com.ujiuye.crmpro.project.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.project.pojo.Module;
import com.ujiuye.crmpro.project.pojo.Project;
import com.ujiuye.crmpro.project.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/module")
public class ModuleController {
    @Autowired
    private ModuleService moduleService;

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
        List<Module> list=moduleService.list(type,keyword);
        //创建分页对象
        PageInfo<Module> page=new PageInfo<>(list);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "list-module";
    }


    @RequestMapping("/tosave")
    public String tosave(){
        return "save-module";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(Module module){
        try {
            int result=moduleService.save(module);
            if (result>0){
                return "true";
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping("/listByAnalysisFk")
    @ResponseBody
    public String listByAnalysisFk(int analysis_fk){
        List<Module> modules = moduleService.listByAnalysisFk(analysis_fk);
        return JSON.toJSONString(modules);
    }
}
