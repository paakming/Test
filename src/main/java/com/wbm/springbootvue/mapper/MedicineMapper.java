package com.wbm.springbootvue.mapper;

import com.wbm.springbootvue.pojo.Medicine;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineMapper {
    int deleteByPrimaryKey(String mid);

    int insert(Medicine record);

    Medicine selectByPrimaryKey(String mid);

    Medicine selectByName(String drugname);

    int updateByPrimaryKeySelective(Medicine record);


}