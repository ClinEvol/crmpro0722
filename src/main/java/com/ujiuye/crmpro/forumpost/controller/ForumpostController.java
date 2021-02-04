package com.ujiuye.crmpro.forumpost.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.customer.pojo.Customer;
import com.ujiuye.crmpro.forumpost.pojo.Evaluate;
import com.ujiuye.crmpro.forumpost.pojo.Forumpost;
import com.ujiuye.crmpro.forumpost.service.EvaluateService;
import com.ujiuye.crmpro.forumpost.service.ForumpostService;
import com.ujiuye.crmpro.forumpost.service.ForumsortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/forumpost")
public class ForumpostController {
    @Autowired
    private ForumpostService forumpostService;
    @Autowired
    private ForumsortService forumsortService;
    @Autowired
    private EvaluateService evaluateService;

    //列表页
    @RequestMapping("/list")
    public String list(
            Model model,
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
            @RequestParam(value = "size",required = false,defaultValue = "10") int size,
            @RequestParam(value = "forumsortFk",required = false,defaultValue = "0") int forumsortFk
    ){
        //更版块新浏览量
        forumsortService.updateClick(forumsortFk);
        //设置分页
        PageHelper.startPage(pageNum,size);
        //查询
        List<Forumpost> list=forumpostService.list(forumsortFk);
        //创建分页对象
        PageInfo<Forumpost> page=new PageInfo<>(list);
        model.addAttribute("list",list);
        model.addAttribute("page",page);
        model.addAttribute("forumsortFk",forumsortFk);
        return "list-forum";
    }

    @RequestMapping("/show")
    public String show(int forumid,Model model){
        Forumpost forumpost = forumpostService.getById(forumid);
        //更新该贴的浏览量
        forumpostService.updateCommentCount(forumid);
        //查询评论列表
        List<Evaluate> list = evaluateService.list(forumid);
        model.addAttribute("forumpost",forumpost);
        model.addAttribute("list",list);
        return "show-forum";
    }
    @RequestMapping("/save")
    @ResponseBody
    public String save(Forumpost forumpost){
        try {
            int result = forumpostService.save(forumpost);
            if(result>0){
                return "true";
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping("/tosave")
    public String tosave() {
        return "save-forum";
    }
}
