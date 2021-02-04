package com.ujiuye.crmpro.customer.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.crmpro.customer.pojo.Customer;
import com.ujiuye.crmpro.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/customer")
@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    //列表页
    @RequestMapping("/list")
    public String list(
            Model model,
            @RequestParam(value = "type",required = false,defaultValue = "0") int type,
            @RequestParam(value = "keyword",required = false,defaultValue = "") String keyword,
            @RequestParam(value = "pageNum",required = false,defaultValue = "1") int pageNum,
            @RequestParam(value = "size",required = false,defaultValue = "5") int size
            ){
        //设置分页
        PageHelper.startPage(pageNum,size);
        //查询
        List<Customer> list=customerService.list(type,keyword);
        //创建分页对象
        PageInfo<Customer> page=new PageInfo<>(list);
        model.addAttribute("list",list);
        model.addAttribute("type",type);
        model.addAttribute("keyword",keyword);
        model.addAttribute("page",page);
        return "list-customer";
    }

    //返回JSON格式
    @RequestMapping("/listJSON")
    @ResponseBody
    public List<Customer> listJSON(){
        return customerService.list(0,null);
    }

    //批量删除
    @ResponseBody
    @RequestMapping("remove")
    public String remove(Integer[] ids){   //1,2,3
        //把数组转为集合
        List<Integer> integers = Arrays.asList(ids);
        System.out.println(integers);
        try {
            int result=customerService.remove(integers);
            if(result>0){
                return "true";
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }


    //删除单个
    @ResponseBody
    @RequestMapping("removeById")
    public String removeById(int id){
        try {
            int result=customerService.removeById(id);
            if (result>0){
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
    public String save(Customer customer){
        try {
            int result=customerService.save(customer);
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
    public String update(Customer customer){
        try {
            int result=customerService.update(customer);
            if (result>0){
                return "true";
            }
            return "false";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    //跳转到修改页
    @RequestMapping("/toupdate")
    public String toUpdate(Model model,int id){
        Customer customer = customerService.getById(id);
        model.addAttribute("customer",customer);
        return "update-customer";
    }

    //跳转到查看
    @RequestMapping("/show")
    public String show(Model model,int id){
        Customer customer = customerService.getById(id);
        model.addAttribute("customer",customer);
        return "show-customer";
    }
    //跳转到添加
    @RequestMapping("/tosave")
    public String tosave(){
        return "save-customer";
    }

}
