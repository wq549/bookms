package com.example.bookms.mapper;

import com.example.bookms.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // 根据用户名查询用户（登录用）
    User findByUsername(String username);
}