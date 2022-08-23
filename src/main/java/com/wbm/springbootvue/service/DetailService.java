package com.wbm.springbootvue.service;

import com.wbm.springbootvue.pojo.Detail;

import java.util.List;

public interface DetailService {
    int deleteByPrimaryKey(Integer did);

    int insert(Detail record);

    Detail selectByPrimaryKey(Integer did);

    int updateByPrimaryKeySelective(Detail record);

    List<Detail> selectBycfid(Integer cfid);
}
