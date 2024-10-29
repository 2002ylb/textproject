package com.example.dongruan.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dongruan.control.Result;
import com.example.dongruan.control.SysException;
import com.example.dongruan.control.UserDTO;
import com.example.dongruan.entity.User;
import org.springframework.stereotype.Service;

public interface Userservice extends IService<User> {
    Result login(UserDTO userDTO) throws SysException;
}
