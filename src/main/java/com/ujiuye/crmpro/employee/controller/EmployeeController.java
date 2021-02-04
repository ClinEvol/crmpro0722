package com.ujiuye.crmpro.employee.controller;

import com.ujiuye.crmpro.employee.pojo.Employee;
import com.ujiuye.crmpro.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/employee")
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/listJSON")
    @ResponseBody
    public List<Employee> listJSON(int position){
        return employeeService.listByPosition(position);
    }


    @RequestMapping("/login")
    @ResponseBody
    public String login(Employee employee, String code, HttpSession session){
        //获取验证码
        String sysCode=(String)session.getAttribute("CODE");
        if(!sysCode.equalsIgnoreCase(code)){
            return "codeError";
        }
        Employee sysEmployee = employeeService.login(employee);
        if(sysEmployee==null){
            return "false";
        }
        session.setAttribute("LOGIN",sysEmployee);
        return "true";
    }

    @RequestMapping("/tologin")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("LOGIN");
        return "redirect:/employee/tologin";
    }

    @RequestMapping("/listAllJSON")
    @ResponseBody
    public List<Employee> listAllJSON(){
        return employeeService.listByName("");
    }

}
