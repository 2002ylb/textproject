package com.example.dongruan.myconfigurer;

import com.example.dongruan.control.interceptor.JWTInterceptor;
import com.example.dongruan.control.interceptor.ProjectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ProjectInterceptor projectInterceptor;
    @Autowired
    private JWTInterceptor jwtInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加自定义拦截器并设置拦截路径
        registry.addInterceptor(projectInterceptor)
                .addPathPatterns("/sss");

//        registry.addInterceptor(jwtInterceptor)
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user/login","/user/register","/student/page");


    }
}