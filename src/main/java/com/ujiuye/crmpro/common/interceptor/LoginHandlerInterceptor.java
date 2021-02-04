package com.ujiuye.crmpro.common.interceptor;

import com.ujiuye.crmpro.employee.pojo.Employee;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String uri=httpServletRequest.getRequestURI();
        if(!uri.contains("login") && !uri.contains("css") && !uri.contains("js") && !uri.contains("fonts")){//不处于登页或登录请求
            //判断是否登录了
            //从session获取LOGIN
            Employee employee=(Employee)httpServletRequest.getSession().getAttribute("LOGIN");
            if(employee==null){//没有登录，转向登录页面
                httpServletResponse.sendRedirect("/employee/tologin");
            }else{//已经登录发，放行
                return true;
            }
        }else{//处于登页或登录请求
            return true;//放行
        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}