package com.example.bookms.service;

import com.example.bookms.entity.User;

public interface UserService {
    User login(String username, String password);
}