package com.wbm.springbootvue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.config.TokenUtils;
import com.wbm.springbootvue.mapper.UserMapper;
import com.wbm.springbootvue.pojo.User;
import com.wbm.springbootvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService{
    
    @Autowired
     UserMapper userMapper;

    @Override
    public User logincheck(User user) {
        user = userMapper.checkLogin(user);
        String token = TokenUtils.genToken(user);
        user.setToken(token);
        return user;
    }

    @Override
    public Integer register(User user) {
        return userMapper.register(user);
    }

    @Override
    public PageInfo<User> All(Integer pageNum, Integer pageSize, String jobid) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userMapper.All(jobid);
        PageInfo<User> pageInfo =new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Integer addUser(User user) {
        user.setPassword("123456");
        return userMapper.addUser(user);
    }

    @Override
    public Integer deleteUser(Integer uid) {
        return userMapper.deleteUser(uid);
    }

    @Override
    public Integer updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public Integer deleteByIds(List<Integer> ids) {
        return userMapper.deleteByIds(ids);
    }

    @Override
    public User getById(Integer uid) {
        return userMapper.getById(uid);
    }

    @Override
    public Integer resetPassword(Integer uid, String password) {
        return userMapper.changePassword(uid,password);
    }

    @Override
    public Integer changePassword(Integer uid,String oldPass,String newPass,String checkPass) {
        User user = userMapper.getById(uid);
        if (user.getPassword().equals(oldPass)&&newPass.equals(checkPass)){
            return userMapper.changePassword(uid,newPass);
        }
        return 0;
    }

}




