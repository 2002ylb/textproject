package com.example.dongruan.control;

import lombok.Data;

/**
 * 接受前端登录请求的参数
 */
@Data
public class UserDTO {
    private  String username;
    private  String password;
    private String token;
}
