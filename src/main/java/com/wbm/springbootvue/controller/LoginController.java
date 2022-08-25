package com.wbm.springbootvue.controller;

import com.wbm.springbootvue.common.Result;
import com.wbm.springbootvue.common.ResultCode;
import com.wbm.springbootvue.pojo.User;
import com.wbm.springbootvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    public Result checkLogin(@RequestBody User user)  {
        user = userService.logincheck(user);
        if(user.getUid()!=null){
            return Result.success(userService.logincheck(user));
        }
        return Result.error(ResultCode.CODE_401,"error");
    }
    @PostMapping(value = "/register")
    public Result register(@RequestBody User user){
        if (userService.register(user)==1){
            return Result.success();
        }
        return Result.error();
    }
}
