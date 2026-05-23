package com.example.bookms.config;

import com.example.bookms.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                // 拦截所有图书接口
                .addPathPatterns("/book/**")
                // 放行登录接口
                .excludePathPatterns("/login");
    }
}