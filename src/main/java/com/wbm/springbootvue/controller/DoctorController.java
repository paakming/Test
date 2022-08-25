package com.wbm.springbootvue.controller;

import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.common.Result;
import com.wbm.springbootvue.common.ResultCode;
import com.wbm.springbootvue.exception.ServiceException;
import com.wbm.springbootvue.pojo.Doctor;
import com.wbm.springbootvue.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping
    public Result doctorList(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageInfo<Doctor> doctorPageInfo =  doctorService.allDoctor(pageNum,pageSize);
        return Result.success(doctorPageInfo);
    }
    @PostMapping(value = "/{jobid}")
    public Result AList(@PathVariable String jobid){
        List<Doctor> doctor = doctorService.Doctor(jobid);
        return Result.success(doctor);
    }
    @PostMapping
    public Result add(@RequestBody Doctor doctor) {
        if (doctor.getName() != null && doctor.getPhone()!=null && doctor.getBirthday()!=null && doctor.getIdentityCard() != null
                && doctor.getDepartment() != null && doctor.getSex() != null &&doctor.getPosition() !=null){
            int r = doctorService.addDoc(doctor);
            if (1 == r) {
                return Result.success();
            }
        }else {
            throw new ServiceException(ResultCode.CODE_500,"必选项不能为空");
        }
        return Result.error();
    }
    @PutMapping
    public Result edit(@RequestBody Doctor doctor){
        if (doctor.getName().isEmpty()||doctor.getPhone().isEmpty()||doctor.getBirthday()==null||doctor.getIdentityCard().isEmpty()||doctor.getDepartment().isEmpty()
                ||doctor.getSex().isEmpty()|| doctor.getPosition().isEmpty()){
            throw new ServiceException(ResultCode.CODE_500,"必选项不能为空");
        }else {
            int r = doctorService.updateDoc(doctor);
            if (r==1){
                return Result.success();
            }
        }
        return Result.error();
    }
        @DeleteMapping(value = "/{jobid}")
        public Result delete(@PathVariable("jobid")String jobid){
            int r = doctorService.delDoc(jobid);
            if (r==1){
                return Result.success();
            }
            return Result.error("删除失败");
        }
        @PostMapping(value = "/ids")
        public Result deleteByIds(@RequestBody List<String> ids){
            int r = doctorService.deleteByJobids(ids);
            if (r>0){
                return Result.success();
            }
            return Result.error("删除失败");
        }
}
