package com.wbm.springbootvue.service.impl;

import com.wbm.springbootvue.mapper.DetailMapper;
import com.wbm.springbootvue.mapper.MedicineMapper;
import com.wbm.springbootvue.pojo.Detail;
import com.wbm.springbootvue.pojo.Medicine;
import com.wbm.springbootvue.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DetailServiceImpl implements DetailService {
    @Autowired
    DetailMapper detailMapper;
    @Autowired
    MedicineMapper medicineMapper;

    @Override
    public int deleteByPrimaryKey(Integer did) {
        return detailMapper.deleteByPrimaryKey(did);
    }

    @Override
    public int insert(Detail record) {
        Medicine medicine = medicineMapper.selectByName(record.getMedicine().getDrugname());
        record.setMid(medicine.getMid());
        record.setIsDeleted("0");
        return detailMapper.insert(record);
    }

    @Override
    public Detail selectByPrimaryKey(Integer did) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Detail record) {
        return detailMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public List<Detail> selectBycfid(Integer cfid) {
        return detailMapper.selectBycfid(cfid);
    }

}
