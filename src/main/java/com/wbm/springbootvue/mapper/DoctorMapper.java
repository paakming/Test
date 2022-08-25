package com.wbm.springbootvue.mapper;

import com.wbm.springbootvue.pojo.Doctor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorMapper {
    List<Doctor> allDoctor(String jobid);
    int deleteByPrimaryKey(String jobid);
    int insert(Doctor record);
    Doctor selectByPrimaryKey(String jobid);
    Doctor selectByName(String name);
    int updateByPrimaryKeySelective(Doctor record);
    int deleteByJobids(List<String> ids);
}