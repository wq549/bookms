package com.example.bookms.interceptor;

import com.example.bookms.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");
        String role = (String) session.getAttribute("role");

        // 1. 未登录直接跳登录页
        if (loginUser == null) {
            response.sendRedirect("/login.html");
            return false;
        }

        String requestURI = request.getRequestURI();

        // 2. 管理员专属：/book/add /book/delete /book/update /index.html
        if (requestURI.startsWith("/book/add") ||
                requestURI.startsWith("/book/delete") ||
                requestURI.startsWith("/book/update") ||
                requestURI.equals("/index.html")) {
            if (!"admin".equals(role)) {
                response.sendRedirect("/user-center.html");
                return false;
            }
        }

        // /book/findAll、/book/search、/book/borrow 等允许已登录普通用户访问
        return true;
    }
}