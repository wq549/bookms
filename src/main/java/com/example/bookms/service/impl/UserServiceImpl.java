package com.example.bookms.service.impl;

import com.example.bookms.entity.User;
import com.example.bookms.mapper.UserMapper;
import com.example.bookms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String password) {
        // 1. 根据用户名查询用户
        User user = userMapper.findByUsername(username);

        // 2. 判断用户是否存在 + 密码是否正确
        if(user == null || !user.getPassword().equals(password)){
            return null;
        }
        return user;
    }
}