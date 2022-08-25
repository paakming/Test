package com.wbm.springbootvue.interceptor;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.wbm.springbootvue.common.ResultCode;
import com.wbm.springbootvue.exception.ServiceException;
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
        if (StrUtil.isBlank(token)){
            throw new ServiceException(ResultCode.CODE_401,"无token，请重新登录");
        }
        //get id
        String uid;
        try {
            uid = JWT.decode(token).getAudience().get(0);
        }catch (JWTDecodeException e){
            throw new ServiceException(ResultCode.CODE_401,"token验证失败,请重新登录");
        }
        User user = userService.getById(Integer.parseInt(uid));
        //search user
        if (user == null){
            throw new ServiceException(ResultCode.CODE_401,"请重新登录");
        }
        // password
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        }catch (JWTVerificationException e){
            throw new ServiceException(ResultCode.CODE_401,"token验证失败，请重新登录");
        }
        return true;
    }
}
