package com.ujiuye.crmpro.project.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.common.utils.FileTypeUtils;
import com.ujiuye.crmpro.common.utils.MyFileUtils;
import com.ujiuye.crmpro.project.pojo.Analysis;
import com.ujiuye.crmpro.project.pojo.Attachment;
import com.ujiuye.crmpro.project.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;

import javax.jws.WebParam;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/attachment")
public class AttachmentController {
    @Autowired
    private AttachmentService attchmentService;

    //列表页
    @RequestMapping("/list")
    public String list(
            Model model,
            @RequestParam(value = "type",required = false,defaultValue = "0") int type,
            @RequestParam(value = "tag",required = false,defaultValue = "") String tag,
            @RequestParam(value = "keyword",required = false,defaultValue = "") String keyword,
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
            @RequestParam(value = "size",required = false,defaultValue = "10") int size
    ){
        //设置分页
        PageHelper.startPage(pageNum,size);
        //查询
        List<Attachment> list=attchmentService.list(type,tag,keyword);
        //创建分页对象
        PageInfo<Attachment> page=new PageInfo<>(list);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("tag",tag);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "list-attachment";
    }

    @RequestMapping("/show")
    public String show(int id, Model model){
        Attachment attachment = attchmentService.getById(id);
        model.addAttribute("attachment",attachment);
        return "show-attachment";
    }

    @RequestMapping("/tosave")
    public String tosave(){
        return "save-attchment";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestParam("myfiles") MultipartFile[] multipartFiles,Attachment attachment){
        List<File> files = MyFileUtils.upload(multipartFiles, null);

        if(files==null || files.size()==0){
            return "file";
        }
        //获取上传的文件对象
        File  file=files.get(0);
        //获取路径
        String name = file.getName();
        attachment.setPath(name);
        //判断文件类型
        int type = FileTypeUtils.getType(name);
        attachment.setType(type);

        try {
            int result=attchmentService.save(attachment);
            if(result>0){
                return "true";
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    //下载
    @RequestMapping("/download")
    public ResponseEntity<byte[]> download(String filename) throws IOException {
        return MyFileUtils.download(filename,null);
    }



}
