package com.wbm.springbootvue.controller;

import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.common.Result;
import com.wbm.springbootvue.pojo.Doctor;
import com.wbm.springbootvue.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/doctor")
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping
    public Result doctorList(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageInfo<Doctor> doctorPageInfo =  doctorService.allDoctor(pageNum,pageSize);
        return Result.success(doctorPageInfo);
    }
    @PostMapping
    public Result add(@RequestBody Doctor doctor) {
        int r = doctorService.addDoc(doctor);
        if (1 == r) {
            return Result.success();
        }
        return Result.error("添加失败");
    }
        @PutMapping
        public Result edit(@RequestBody Doctor doctor){
            int r = doctorService.updateDoc(doctor);
            if (r==1){
                return Result.success();
            }
            return Result.error("error");
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
