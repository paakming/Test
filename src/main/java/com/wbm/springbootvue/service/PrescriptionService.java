package com.wbm.springbootvue.service;

import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.pojo.Prescription;

import java.util.List;

public interface PrescriptionService {
    PageInfo<Prescription> allPrescription(Integer pageNum, Integer pageSize);

    List<Prescription> allPrescription();

    Integer deleteByPrimaryKey(Integer cfid);

    Integer insert(String pname,String name, String message,String drugname,String usage,String quantity);

    List<Prescription> selectByPrimaryKey(Integer cfid);

    Integer updateByPrimaryKeySelective(Prescription prescription);

    Integer deleteBycfids(List<Integer> ids);

    Integer newPrescription(Integer pid,String jobid, String message,String drugname,String usage,String quantity,Integer rid);
}
