package com.ujiuye.crmpro.message.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.common.utils.MyFileUtils;
import com.ujiuye.crmpro.customer.pojo.Customer;
import com.ujiuye.crmpro.message.pojo.Notice;
import com.ujiuye.crmpro.message.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    //列表页
    @RequestMapping("/list")
    public String list(
            Model model,
            @RequestParam(value = "keyword",required = false,defaultValue = "") String keyword,
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
            @RequestParam(value = "size",required = false,defaultValue = "5") int size
    ){
        //设置分页
        PageHelper.startPage(pageNum,size);
        //查询
        List<Notice> list=noticeService.list(keyword);
        //创建分页对象
        PageInfo<Notice> page=new PageInfo<>(list);
        model.addAttribute("list",list);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "list-notice";
    }

    //跳转到添加
    @RequestMapping("/tosave")
    public String tosave(){
        return "save-notice";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestParam("myfiles") MultipartFile[] multipartFiles,Notice notice){
        List<File> files = MyFileUtils.upload(multipartFiles, null);
        if(files!=null && files.size()>0){
            //获取上传的文件对象
            File  file=files.get(0);
            notice.setPath(file.getName());
        }
        notice.setDate(new Date());
        try {
            int result=noticeService.save(notice);
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
        Notice notice = noticeService.getById(id);
        model.addAttribute("notice",notice);
        return "show-notice";
    }
}
