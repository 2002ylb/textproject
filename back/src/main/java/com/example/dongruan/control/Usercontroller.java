package com.example.dongruan.control;

import cn.hutool.core.util.StrUtil;
import com.example.dongruan.entity.User;
import com.example.dongruan.mapper.Usermapper;
import com.example.dongruan.service.Userservice;
import com.example.dongruan.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class Usercontroller {
    @Autowired
    private Usermapper usermapper;
    @Autowired
    private Userservice userservice;
    @GetMapping("text1")
    public void text1() throws SysException {

        try {
            int a=1/0;
        } catch (Exception e) {
            throw new SysException(e,Code.SYS_ERR);
        }


    }
    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) throws SysException {

        String username = userDTO.getUsername();
        String password = userDTO.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){

            throw  new SysException("名字和密码为空",Code.SYS_ERR);
        }else {
       return userservice.login(userDTO);
        }
    }

    @PostMapping("/register")
    public boolean register(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        if(StrUtil.isBlank(username) || StrUtil.isBlank(password)){
            return  false;
        }else {
            return userservice.save(user);
        }
    }

    @GetMapping("text2")
    public void text2() throws TextException {
        int a=10;
       if(a==10){
          throw   new TextException("成功的异常自定义");
       }
    }



//一对一  找用户和钱
    @GetMapping("finduseraccount")
    public List<User> finduseraccount() {
       return usermapper.findaccount();


    }
}
