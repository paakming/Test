package com.wbm.springbootvue.service;

import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.pojo.User;

import java.util.List;

/**
* @author 25419
* @description 针对表【user】的数据库操作Service
* @createDate 2022-07-16 12:13:24
*/

public interface UserService  {
    User logincheck(User user);
    Integer register(User user);
    PageInfo<User> All(Integer pageNum, Integer pageSize,String jobid);
    Integer addUser(User user);
    Integer deleteUser(Integer uid);
    Integer updateUser(User user);
    Integer deleteByIds(List<Integer> ids);
    User getById(Integer uid);
    Integer resetPassword(Integer uid,String password);
    Integer changePassword(Integer uid,String oldPass,String newPass,String checkPass);
}
