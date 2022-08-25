package com.wbm.springbootvue.controller;

import com.wbm.springbootvue.common.Result;
import com.wbm.springbootvue.common.ResultCode;
import com.wbm.springbootvue.exception.ServiceException;
import com.wbm.springbootvue.pojo.Detail;
import com.wbm.springbootvue.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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
        if (detail.getMedicine().getDrugname()==null||detail.getQuantity()==null||detail.getUsage()==null){
            throw new ServiceException(ResultCode.CODE_500,"必填项不能为空");
        }else {
            int r = detailService.insert(detail);
            if (1 == r) {
                return Result.success();
            }
        }
        return Result.error();
    }
    @PutMapping
    public Result edit(@RequestBody Detail detail){
        if (detail.getMedicine().getDrugname().isEmpty()||detail.getQuantity().isEmpty()||detail.getUsage().isEmpty()){
            throw new ServiceException(ResultCode.CODE_500,"必填项不能为空");
        }else {
            int r = detailService.updateByPrimaryKeySelective(detail);
            if (r==1){
                return Result.success();
            }
        }
        return Result.error();
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
