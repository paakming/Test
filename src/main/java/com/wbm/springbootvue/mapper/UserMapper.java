package com.wbm.springbootvue.mapper;

import com.wbm.springbootvue.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserMapper  {
    User checkLogin(User user);
    Integer register(User user);
    List<User> All(@Param("jobid")String jobid);
    Integer addUser(User user);
    Integer deleteUser(Integer uid);
    Integer updateUser(User user);
    Integer deleteByIds(List<Integer> ids);
    User getById(Integer uid);
    Integer changePassword(@Param("uid")Integer uid,@Param("password")String password);
}




