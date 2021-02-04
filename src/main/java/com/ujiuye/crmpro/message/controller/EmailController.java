package com.ujiuye.crmpro.message.controller;

import com.ujiuye.crmpro.common.utils.EmailScheduler;
import com.ujiuye.crmpro.common.utils.EmailUtil;
import com.ujiuye.crmpro.employee.pojo.Archives;
import com.ujiuye.crmpro.employee.service.ArchivesService;
import com.ujiuye.crmpro.message.pojo.Email;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;

@Controller
@RequestMapping("/email")
public class EmailController {
    @Autowired
    private ArchivesService archivesService;

    @RequestMapping("/tosave")
    public String tosave(){
        return "save-email";
    }


    @RequestMapping("save")
    @ResponseBody
    public String save(int eid, Email email){
        //去档案查到该eid邮箱
        Archives archives = archivesService.getByEmpFk(eid);
        if(archives==null || archives.getEmail()==null || archives.getEmail().equals("")){
            return "noEmail";
        }
        email.setReceive(archives.getEmail());
        //定时发送
        if(email.getIsTime()!=null && email.getIsTime().equals("on")){
            try {
                EmailScheduler.sendEmail(email);
                return "true";
            } catch (SchedulerException e) {
                e.printStackTrace();
                return "false";
            }
        }

        //立即发送
        try {
            //调用工具类
            EmailUtil.sendEmail(email);
            return "true";
        } catch (MessagingException e) {
            e.printStackTrace();
            return "false";
        }
    }
}
