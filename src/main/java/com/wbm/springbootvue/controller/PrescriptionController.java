package com.wbm.springbootvue.controller;

import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.common.Result;
import com.wbm.springbootvue.pojo.Prescription;
import com.wbm.springbootvue.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/prescription")
public class PrescriptionController {
    @Autowired
    PrescriptionService prescriptionService;

    @GetMapping
    public Result doctorList(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        PageInfo<Prescription> PageInfo =  prescriptionService.allPrescription(pageNum,pageSize);
        return Result.success(PageInfo);
    }
    @PostMapping
    public Result add(@RequestBody Prescription prescription) {
        int r = prescriptionService.insert(prescription);
        if (1 == r) {
            return Result.success();
        }
        return Result.error("添加失败");
    }
    @PutMapping
    public Result edit(@RequestBody Prescription prescription){
        int r = prescriptionService.updateByPrimaryKeySelective(prescription);
        if (r==1){
            return Result.success();
        }
        return Result.error("error");
    }
    @DeleteMapping(value = "/{cfid}")
    public Result delete(@PathVariable("cfid")Integer cfid){
        int r = prescriptionService.deleteByPrimaryKey(cfid);
        if (r==1){
            return Result.success();
        }
        return Result.error("删除失败");
    }
    @PostMapping(value = "/ids")
    public Result deleteByIds(@RequestBody List<Integer> ids){
        int r = prescriptionService.deleteBycfids(ids);
        if (r>0){
            return Result.success();
        }
        return Result.error("删除失败");
    }
    @PostMapping(value = "/{pid}/{jobid}/{message}/{drugname}/{usage}/{quantity}/{rid}")
    public Result newPrescription(@PathVariable(value = "pid")Integer pid,@PathVariable(value = "jobid")String jobid,
                                  @PathVariable("message")String message, @PathVariable(value = "drugname")String drugname,
                                  @PathVariable(value = "usage")String usage,@PathVariable(value = "quantity")String quantity,
                                  @PathVariable(value = "rid")Integer rid){
        int r = prescriptionService.newPrescription(pid, jobid, message, drugname, usage, quantity,rid);
        if (r>0){
            return Result.success(r);
        }
        return Result.error("开具处方失败");
    }
}
