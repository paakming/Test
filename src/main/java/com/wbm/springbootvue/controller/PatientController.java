package com.wbm.springbootvue.controller;

import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.common.Result;
import com.wbm.springbootvue.pojo.Patient;
import com.wbm.springbootvue.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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
        int r = patientService.insert(patient);
        if (1 == r) {
            return Result.success();
        }
        return Result.error("添加失败");
    }
    @PutMapping
    public Result edit(@RequestBody Patient patient){
        int r = patientService.updateByPrimaryKey(patient);
        if (r==1){
            return Result.success();
        }
        return Result.error("error");
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
