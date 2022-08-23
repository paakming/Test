package com.wbm.springbootvue.service;

import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.pojo.Patient;

import java.util.List;

public interface PatientService {
    Integer deleteByPrimaryKey(Integer pid);

    Integer insert(Patient patient);

    Patient selectByPrimaryKey(Integer pid);

    Integer updateByPrimaryKey(Patient patient);

    PageInfo<Patient> allPatient(Integer pageNum, Integer pageSize);

    Integer deleteBypids(List<Integer> ids);
}
