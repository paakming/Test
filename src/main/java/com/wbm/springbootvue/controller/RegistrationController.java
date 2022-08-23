package com.wbm.springbootvue.controller;

import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.common.Result;
import com.wbm.springbootvue.pojo.Registration;
import com.wbm.springbootvue.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/registration")
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;

    @GetMapping
    public Result getAll(@RequestParam(required = false)String jobid ,@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageInfo<Registration> registrations = registrationService.selectByjobid(jobid, pageNum, pageSize);
        return Result.success(registrations);
    }

    @PutMapping(value = "/{rid}")
    public Result update(@PathVariable Integer rid){
        Registration registration = new Registration();
        registration.setRid(rid);
        int r = registrationService.updateByPrimaryKeySelective(registration);
        if (1==r){
            return Result.success();
        }
        return Result.error();
    }

}
