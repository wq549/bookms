package com.example.bookms.controller;

import com.example.bookms.common.Result;
import com.example.bookms.entity.User;
import com.example.bookms.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    // 登录接口
    @PostMapping("/login")
    public Result login(
            @RequestParam String username,
            @RequestParam String password,
            HttpSession session) {

        User user = userService.login(username, password);

        if (user == null) {
            return Result.fail("用户名或密码错误");
        }

        // 登录成功，把用户信息存入session
        session.setAttribute("loginUser", user);
        return Result.success(user);
    }

    @PostMapping("/logout")
    public Result logout(HttpSession session){
        session.removeAttribute("loginUser");
        return Result.success("退出登录成功");
    }
}