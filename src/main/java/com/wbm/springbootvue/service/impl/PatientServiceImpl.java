package com.wbm.springbootvue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.mapper.PatientMapper;
import com.wbm.springbootvue.pojo.Patient;
import com.wbm.springbootvue.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientMapper patientMapper;
    @Override
    public Integer deleteByPrimaryKey(Integer pid) {
        return patientMapper.deleteByPrimaryKey(pid);
    }

    @Override
    public Integer insert(Patient patient) {
        patient.setIsDeleted("0");
        return patientMapper.insert(patient);
    }

    @Override
    public Patient selectByPrimaryKey(Integer pid) {
        return null;
    }

    @Override
    public Integer updateByPrimaryKey(Patient patient) {
        return patientMapper.updateByPrimaryKeySelective(patient);
    }

    @Override
    public PageInfo<Patient> allPatient(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Patient> list = patientMapper.allPatient();
        PageInfo<Patient> pageInfo =new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Integer deleteBypids(List<Integer> ids) {
        return patientMapper.deleteBypids(ids);
    }

}
