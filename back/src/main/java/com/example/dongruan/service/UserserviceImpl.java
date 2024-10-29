package com.example.dongruan.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.injector.methods.SelectOne;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dongruan.control.Code;
import com.example.dongruan.control.Result;
import com.example.dongruan.control.SysException;
import com.example.dongruan.control.UserDTO;
import com.example.dongruan.entity.User;
import com.example.dongruan.mapper.Usermapper;
import com.example.dongruan.utils.TokenUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service

public class UserserviceImpl extends ServiceImpl<Usermapper, User> implements Userservice {
@Autowired
private Usermapper usermapper;
    @Override
    public Result login(UserDTO userDTO) throws SysException {

         QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
            userQueryWrapper.eq("username",userDTO.getUsername())
                            .eq("password",userDTO.getPassword());
       User user1= usermapper.selectOne(userQueryWrapper);

        if (user1!=null) {
            String token= TokenUtils.genToken(user1.getId().toString(),user1.getPassword());

            userDTO.setToken(token);

//            // 获取当前的 HttpSession
//            HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
//
//            // 将 token 存储到 session 中
//            session.setAttribute("token", token);

            return new Result(userDTO,200,"登录成功");
        }else{
            throw new SysException("user为空了",Code.SYS_ERR);
        }
    }
}
