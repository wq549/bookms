package com.example.bookms.controller;

import com.example.bookms.common.Result;
import com.example.bookms.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/info")
    public Result info(HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        return Result.success(loginUser);
    }
}