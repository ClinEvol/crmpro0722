package com.ujiuye.crmpro.system.controller;

import com.alibaba.fastjson.JSON;
import com.ujiuye.crmpro.system.pojo.Sources;
import com.ujiuye.crmpro.system.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sources")
public class SourcesController {

    @Autowired
    private SourcesService sourcesService;

    @RequestMapping(value = "list",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String list(){
        List<Sources> list = sourcesService.list();
        return JSON.toJSONString(list);
    }

    @RequestMapping(value = "listAll",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String listAll(){
        List<Sources> list = sourcesService.listAll();
        return JSON.toJSONString(list);
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(int id){
        Sources sources = sourcesService.getById(id);
        return JSON.toJSONString(sources);
    }


    @RequestMapping("/remove")
    @ResponseBody
    public String remove(int id){
        try {
            int result = sourcesService.remove(id);
            if(result>0){
                return "true";
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }


    @RequestMapping("/save")
    @ResponseBody
    public String save(Sources sources){
        try {
            int result = sourcesService.save(sources);
            if(result>0){
                return "true";
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(Sources sources){
        try {
            int result = sourcesService.update(sources);
            if(result>0){
                return "true";
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }
    @RequestMapping("/toResources")
    public String toResources(){
        return "list-resources";
    }

}
