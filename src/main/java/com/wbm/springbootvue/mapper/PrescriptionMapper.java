package com.wbm.springbootvue.mapper;

import com.wbm.springbootvue.pojo.Prescription;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionMapper {
    int deleteByPrimaryKey(Integer cfid);

    int insert(Prescription record);

    Prescription selectByPrimaryKey(Integer cfid);

    int updateByPrimaryKeySelective(Prescription record);

    List<Prescription> allPrescription();

    int deleteBycfids(List<Integer> ids);
}