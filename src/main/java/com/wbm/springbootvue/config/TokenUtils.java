package com.wbm.springbootvue.config;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.wbm.springbootvue.pojo.User;

import java.util.Date;

public class TokenUtils {
    public static String genToken(User user) {
        return JWT.create().withAudience(Integer.toString(user.getUid()))
                .withExpiresAt(DateUtil.offsetHour(new Date(),2))
                .sign(Algorithm.HMAC256(user.getPassword()));
    }
}

