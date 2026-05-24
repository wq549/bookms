package com.example.bookms.interceptor;

import com.example.bookms.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        User loginUser = (User) request.getSession().getAttribute("loginUser");
        if(loginUser == null){
            // 重定向到登录页面
            response.sendRedirect("/login.html");
            return false;
        }
        return true;
    }
}