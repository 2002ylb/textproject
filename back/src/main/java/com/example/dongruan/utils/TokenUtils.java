package com.example.dongruan.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

public class TokenUtils {

    /**
     * 生成token
     */
    public static String genToken(String userid,String sign){
        return JWT.create().withAudience(userid) // 将 user id 保存到 token 里面
                .withExpiresAt(DateUtil.offsetHour(new Date(),2)) //2小时后token过期
                .sign(Algorithm.HMAC256(sign))  ; // 以 password 作为 token 的密钥




    }
}
