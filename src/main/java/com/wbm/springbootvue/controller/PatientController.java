package com.wbm.springbootvue.controller;

import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.common.Result;
import com.wbm.springbootvue.common.ResultCode;
import com.wbm.springbootvue.exception.ServiceException;
import com.wbm.springbootvue.pojo.Patient;
import com.wbm.springbootvue.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/patient")
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping
    public Result patientList(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageInfo<Patient> patientPageInfo =  patientService.allPatient(pageNum,pageSize);
        return Result.success(patientPageInfo);
    }
    @PostMapping
    public Result add(@RequestBody Patient patient) {
        String name = patient.getPname();
       // String birthday = patient.getBirthday().toString();
        String phone = patient.getPhone();
        String sex = patient.getSex();
        String id = patient.getIdentityCard();
        boolean flag = true;
        if (name == null||name.isEmpty()){
            flag = false;
        }
        /*if (birthday == null||birthday.isEmpty()){
            flag = false;
        }*/
        if (phone == null||phone.isEmpty()){
            flag = false;
        }
        if (sex == null||sex.isEmpty()){
            flag = false;
        }
        if (id == null||id.isEmpty()){
            flag = false;
        }
        if (flag){
            int r = patientService.insert(patient);
            if (1 == r) {
                return Result.success();
            }
        }
        else {
            throw new ServiceException(ResultCode.CODE_500,"必填项不能为空");
        }
        return Result.error();
    }
    @PutMapping
    public Result edit(@RequestBody Patient patient){
        System.out.println(patient);
        if (patient.getPname().isEmpty()||patient.getIdentityCard().isEmpty()||patient.getPhone().isEmpty()||patient.getSex().isEmpty()){
            throw new ServiceException(ResultCode.CODE_500,"必填项不能为空");
        }else {
            int r = patientService.updateByPrimaryKey(patient);
            if (r==1){
                return Result.success();
            }
        }
        return Result.error();
    }
    @DeleteMapping(value = "/{pid}")
    public Result delete(@PathVariable("pid") Integer pid){
        int r = patientService.deleteByPrimaryKey(pid);
        if (r==1){
            return Result.success();
        }
        return Result.error("删除失败");
    }
    @PostMapping(value = "/ids")
    public Result deleteByIds(@RequestBody List<Integer> ids){
        int r = patientService.deleteBypids(ids);
        if (r>0){
            return Result.success();
        }
        return Result.error("删除失败");
    }
}
