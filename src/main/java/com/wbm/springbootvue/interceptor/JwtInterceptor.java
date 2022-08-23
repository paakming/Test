package com.wbm.springbootvue.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.wbm.springbootvue.common.Result;
import com.wbm.springbootvue.pojo.User;
import com.wbm.springbootvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtInterceptor implements HandlerInterceptor {

    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (!(handler instanceof HandlerMethod)){
            return true;
        }
        if (token == null){
            Result.error("no token");
            return false;
        }
        //get id
        String uid = JWT.decode(token).getAudience().get(0);
        User user = userService.getById(Integer.parseInt(uid));
        //search user
        if (user == null){
            Result.error("user isn't exist");
            return false;
        }
        // password
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        jwtVerifier.verify(token);
        return true;
    }
}
