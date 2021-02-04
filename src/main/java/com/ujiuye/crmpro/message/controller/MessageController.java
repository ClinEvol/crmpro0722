package com.ujiuye.crmpro.message.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.common.utils.MyFileUtils;
import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.message.pojo.Message;
import com.ujiuye.crmpro.message.pojo.Notice;
import com.ujiuye.crmpro.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.Date;
import java.util.List;

@RequestMapping("/message")
@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;

    //列表页
    @RequestMapping("/list")
    public String list(
            Model model, HttpSession session,
            @RequestParam(value = "type",required = false,defaultValue = "4") int type,
            @RequestParam(value = "keyword",required = false,defaultValue = "") String keyword,
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
            @RequestParam(value = "size",required = false,defaultValue = "5") int size
    ){
        Employee employee = (Employee)session.getAttribute("LOGIN");
        //设置分页
        PageHelper.startPage(pageNum,size);
        //查询
        List<Message> list=messageService.listInbox(type,employee.getEid());
        //创建分页对象
        PageInfo<Message> page=new PageInfo<>(list);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "list-message";
    }

    //跳转到添加
    @RequestMapping("/tosave")
    public String tosave(Model model,@RequestParam(value = "eid",required = false,defaultValue = "0") int eid){
        model.addAttribute("eid",eid);
        return "save-message";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestParam("myfiles") MultipartFile[] multipartFiles, Message message){
        List<File> files = MyFileUtils.upload(multipartFiles, null);
        if(files!=null && files.size()>0){
            //获取上传的文件对象
            File  file=files.get(0);
            message.setPath(file.getName());
        }

        message.setTime(new Date());

        try {
            int result=messageService.save(message);
            if (result>0){
                return "true";
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    //跳转到查看
    @RequestMapping("/show")
    public String show(Model model,int id){
        Message message = messageService.getById(id);
        //修改未读为已读
        message.setStatus(0);
        messageService.update(message);

        model.addAttribute("message",message);
        return "show-message";
    }
}
