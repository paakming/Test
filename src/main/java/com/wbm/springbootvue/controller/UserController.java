package com.wbm.springbootvue.controller;

import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.common.Result;
import com.wbm.springbootvue.pojo.User;
import com.wbm.springbootvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public PageInfo<User> findByPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize,@RequestParam(required = false)String jobid) {
        PageInfo<User> userList =  userService.All(pageNum,pageSize,jobid);
        return userList;
    }
    @PostMapping
    public Result add(@RequestBody User user){
        int r = userService.addUser(user);
        if (1==r){
            return Result.success();
        }
        return Result.error("添加失败");
    }

    @PutMapping
    public Result edit(@RequestBody User user){
        int r = userService.updateUser(user);
        if (r==1){
            return Result.success();
        }
        return Result.error("error");
    }
    @DeleteMapping(value = "/{uid}")
    public Result delete(@PathVariable("uid")Integer uid){
        int r = userService.deleteUser(uid);
        if (r==1){
            return Result.success();
        }
        return Result.error("删除失败");
    }
    @PostMapping(value = "/ids")
    public Result deleteByIds(@RequestBody List<Integer> ids){
        int r =userService.deleteByIds(ids);
        if (r>0){
            return Result.success();
        }
        return Result.error("删除失败");
    }
    @PutMapping(value = "/reset")
    public Result resetPassword(@RequestBody Integer uid){
        if (1==userService.resetPassword(uid,"123456")){
            return Result.success();
        }
        return Result.error();
    }
    @PostMapping(value = "/changePwd/{uid}/{oldPass}/{newPass}/{checkPass}")
    public Result changePassword(@PathVariable Integer uid,@PathVariable String oldPass,@PathVariable String newPass,@PathVariable String checkPass){
       if (1==userService.changePassword(uid, oldPass, newPass, checkPass)){
            return Result.success();
        }
        return Result.error("修改密码失败");
    }
}
