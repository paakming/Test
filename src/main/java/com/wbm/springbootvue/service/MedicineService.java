package com.wbm.springbootvue.service;

import com.wbm.springbootvue.pojo.Medicine;

public interface MedicineService {
    int deleteByPrimaryKey(String mid);

    int insert(Medicine record);

    Medicine selectByPrimaryKey(String mid);

    Medicine selectByName(String drugname);

    int updateByPrimaryKeySelective(Medicine record);
}
