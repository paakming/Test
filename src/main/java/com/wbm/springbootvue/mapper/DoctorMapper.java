package com.wbm.springbootvue.mapper;

import com.wbm.springbootvue.pojo.Doctor;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DoctorMapper {
    List<Doctor> allDoctor();
    int deleteByPrimaryKey(String jobid);
    int insert(Doctor record);
    int insertSelective(Doctor record);
    Doctor selectByPrimaryKey(String jobid);
    int updateByPrimaryKeySelective(Doctor record);
    int updateByPrimaryKey(Doctor record);
    int deleteByJobids(List<String> ids);
}