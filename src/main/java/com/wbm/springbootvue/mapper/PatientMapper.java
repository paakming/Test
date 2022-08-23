package com.wbm.springbootvue.mapper;

import com.wbm.springbootvue.pojo.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientMapper {
    int deleteByPrimaryKey(Integer pid);

    int insert(Patient record);

    Patient selectByPrimaryKey(Integer pid);

    int updateByPrimaryKeySelective(Patient record);

    List<Patient> allPatient();

    Integer deleteBypids(List<Integer> ids);
}