package com.wbm.springbootvue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.mapper.DoctorMapper;
import com.wbm.springbootvue.pojo.Doctor;
import com.wbm.springbootvue.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    DoctorMapper doctorMapper;
    @Override
    public PageInfo<Doctor> allDoctor(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Doctor> list = doctorMapper.allDoctor();
        PageInfo<Doctor> pageInfo =new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Integer addDoc(Doctor doctor) {
        doctor.setIsDeleted("0");
        return doctorMapper.insert(doctor);
    }

    @Override
    public Integer updateDoc(Doctor doctor) {
        return doctorMapper.updateByPrimaryKeySelective(doctor);
    }

    @Override
    public Integer delDoc(String jobid) {
        return doctorMapper.deleteByPrimaryKey(jobid);
    }

    @Override
    public Integer deleteByJobids(List<String> ids) {
        return doctorMapper.deleteByJobids(ids);
    }
}
