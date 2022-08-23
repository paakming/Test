package com.wbm.springbootvue.service.impl;

import com.wbm.springbootvue.mapper.MedicineMapper;
import com.wbm.springbootvue.pojo.Medicine;
import com.wbm.springbootvue.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicineServiceImpl implements MedicineService {
    @Autowired
    MedicineMapper medicineMapper;
    @Override
    public int deleteByPrimaryKey(String mid) {
        return 0;
    }

    @Override
    public int insert(Medicine record) {
        return 0;
    }

    @Override
    public Medicine selectByPrimaryKey(String mid) {
        return null;
    }

    @Override
    public Medicine selectByName(String drugname) {
        return selectByName(drugname);
    }

    @Override
    public int updateByPrimaryKeySelective(Medicine record) {
        return 0;
    }
}
