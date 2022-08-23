package com.wbm.springbootvue.mapper;

import com.wbm.springbootvue.pojo.Detail;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailMapper {
    int deleteByPrimaryKey(Integer did);

    int insert(Detail record);

    Detail selectByPrimaryKey(Integer did);

    int updateByPrimaryKeySelective(Detail record);

    List<Detail> selectBycfid(Integer cfid);
}