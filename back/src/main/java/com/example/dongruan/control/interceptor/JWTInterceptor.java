package com.example.dongruan.control.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Verification;
import com.example.dongruan.control.Code;
import com.example.dongruan.control.SysException;
import com.example.dongruan.entity.User;
import com.example.dongruan.service.Userservice;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
@Component
public class JWTInterceptor implements HandlerInterceptor {
    @Autowired
    private Userservice userservice;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        System.out.printf("已经拦截过了"+token);
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        if(StrUtil.isBlank(token)){
            throw new SysException("token is null", Code.SYS_ERR);
        }
        String useid;
        try {
            useid = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException e) {
            throw new SysException("token的useid is null", Code.SYS_ERR);
        }

        User userone = userservice.getById(useid);
        if(userone==null){
            throw new SysException("token查用户 is null", Code.SYS_ERR);
        }
        //用户密码加签验证token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(userone.getPassword())).build();
        try {
            jwtVerifier.verify(token);//验证token
        } catch (JWTVerificationException e) {
            throw new SysException("token验证失败", Code.SYS_ERR);
        }


        return true;
    }


}
