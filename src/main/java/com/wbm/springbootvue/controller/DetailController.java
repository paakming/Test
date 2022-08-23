package com.wbm.springbootvue.controller;

import com.wbm.springbootvue.common.Result;
import com.wbm.springbootvue.pojo.Detail;
import com.wbm.springbootvue.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/detail")
public class DetailController {
    @Autowired
    DetailService detailService;

    @GetMapping(value = "/{cfid}")
    public Result getAllDetail(@PathVariable Integer cfid){
        List<Detail> details = detailService.selectBycfid(cfid);
        return Result.success(details);
    }
    @PostMapping
    public Result add(@RequestBody Detail detail) {
        int r = detailService.insert(detail);
        if (1 == r) {
            return Result.success();
        }
        return Result.error("添加失败");
    }
    @PutMapping
    public Result edit(@RequestBody Detail detail){
        System.out.println(detail);
        int r = detailService.updateByPrimaryKeySelective(detail);
        if (r==1){
            return Result.success();
        }
        return Result.error("error");
    }
    @DeleteMapping(value = "/{did}")
    public Result delete(@PathVariable("did")Integer did){
        int r = detailService.deleteByPrimaryKey(did);
        if (r==1){
            return Result.success();
        }
        return Result.error("删除失败");
    }
}
