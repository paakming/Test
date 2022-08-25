package com.wbm.springbootvue.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.wbm.springbootvue.common.Result;
import com.wbm.springbootvue.pojo.Prescription;
import com.wbm.springbootvue.pojo.Registration;
import com.wbm.springbootvue.pojo.User;
import com.wbm.springbootvue.service.PrescriptionService;
import com.wbm.springbootvue.service.RegistrationService;
import com.wbm.springbootvue.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/echarts")
public class EchartsController {
    @Autowired
    UserService userService;
    @Autowired
    PrescriptionService prescriptionService;
    @Autowired
    RegistrationService registrationService;

    @GetMapping(value = "/user")
    public Result getUserNum(){
        List<User> list = userService.list();
        int q1 = 0;
        int q2 = 0;
        int q3 = 0;
        int q4 = 0;
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
        return Result.success(arrayList);
    }
    @GetMapping(value = "/prescription")
    public Result getPrescriptionNum(){
        List<Prescription> prescriptions = prescriptionService.allPrescription();
        int q1 = 0;
        int q2 = 0;
        int q3 = 0;
        int q4 = 0;
        for (Prescription prescription : prescriptions) {
            Date date = prescription.getOpentime();
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
        return Result.success(arrayList);
    }
    @GetMapping(value = "/registrationNum")
    public Result getRegistrationNum(){
        List <Registration> registrations = registrationService.all();
        return Result.success(registrations);
    }
}
