package com.ujiuye.crmpro.task.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.project.pojo.Analysis;
import com.ujiuye.crmpro.task.pojo.Task;
import com.ujiuye.crmpro.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;
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
        List<Task> list=taskService.list(type,keyword,0);
        //创建分页对象
        PageInfo<Task> page=new PageInfo<>(list);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "list-task";
    }
    //我的列表页
    @RequestMapping("/mylist")
    public String myList(
            Model model, HttpSession session,
            @RequestParam(value = "type",required = false,defaultValue = "0") int type,
            @RequestParam(value = "keyword",required = false,defaultValue = "") String keyword,
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
            @RequestParam(value = "size",required = false,defaultValue = "10") int size
    ){
        //设置分页
        PageHelper.startPage(pageNum,size);
        Employee employee=(Employee)session.getAttribute("LOGIN");
        //查询
        List<Task> list=taskService.list(type,keyword,employee.getEid());
        //创建分页对象
        PageInfo<Task> page=new PageInfo<>(list);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "list-mytask";
    }
    @RequestMapping("/tosave")
    public String tosave(){
        return "save-task";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(Task task){
        try {
            int result=taskService.save(task);
            if (result>0){
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
    public String update(Task task){
        try {
            int result=taskService.update(task);
            if (result>0){
                return "true";
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

}
