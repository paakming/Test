package com.wbm.springbootvue;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
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
import java.util.Date;
import java.util.List;

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
    @Test
    public void echart(){
        List<User> list = userService.list();
        int q1 = 0;
        int q2 = 0;
        int q3 = 0;
        int q4 = 0;
        list.forEach(user -> {
            System.out.println(user);
        });
        for (User user : list) {
            Date date = user.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(date);
            if (quarter.equals(Quarter.Q1)){
                q1 += 1;
            }
            if (quarter.equals(Quarter.Q2)){
                q2 += 1;
            }
            if (quarter.equals(Quarter.Q3)){
                q3 += 1;
            }
            if (quarter.equals(Quarter.Q4)){
                q4 += 1;
            }
        }
        ArrayList arrayList = CollUtil.newArrayList(q1,q2,q3,q4);
        arrayList.forEach(System.out::println);
    }
}
