package com.wbm.springbootvue.service;

import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.pojo.Doctor;
import java.util.List;

public interface DoctorService {
    PageInfo<Doctor> allDoctor(Integer pageNum, Integer pageSize);
    Integer addDoc(Doctor doctor);
    Integer updateDoc(Doctor doctor);
    Integer delDoc(String jobid);
    Integer deleteByJobids(List<String> ids);
}
