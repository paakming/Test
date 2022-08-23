package com.wbm.springbootvue;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.mapper.UserMapper;
import com.wbm.springbootvue.pojo.Doctor;
import com.wbm.springbootvue.pojo.User;
import com.wbm.springbootvue.service.DoctorService;
import com.wbm.springbootvue.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class SpringbootvueApplicationTests {
    @Autowired
    UserMapper userMapper;
    @Autowired
    DoctorService doctorService;
    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
       PageInfo<User> list =  userService.All(1,10,"19001");
       list.getList().forEach(System.out::println);
       //Doctor doctor = doctorMapper.selectByPrimaryKey("19001");
    }
    @Test
    public void testdoctor(){
        PageInfo<Doctor> list =  doctorService.allDoctor(1,10);
        list.getList().forEach(System.out::println);
    }
    @Test
    public void testdelete(){
        userMapper.deleteUser(16);
    }
    @Test
    public void testupdate(){
        User user = new User();
        user.setUid(15);
        user.setJobid("199999");
        user.setEmail("2541@q.com");
        //user.setPhone("1831288");
        //user.setIsManager("管理员");
        userMapper.updateUser(user);
    }
    @Test
    public void testinsert(){
        User user = new User();
        user.setJobid("199999");
        user.setPassword("254156566");
        user.setPhone("1831288");
        user.setIsManager("管理员");
        userMapper.addUser(user);
    }
    @Test
    public void deleteids(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(14);
        list.add(13);
        list.add(12);
        userService.deleteByIds(list);
    }
    @Test
    public void userInfo(){
        User user = new User();
        user.setJobid("19001");
        user.setPassword("111");

        System.out.println(userService.logincheck(user));
    }

    @Test
    public void Time(){

        System.out.println(DateUtil.date(System.currentTimeMillis()));
    }
}
