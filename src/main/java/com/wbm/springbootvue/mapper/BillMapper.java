package com.wbm.springbootvue.mapper;

import com.wbm.springbootvue.pojo.Bill;
import org.springframework.stereotype.Repository;

@Repository
public interface BillMapper {
    int deleteByPrimaryKey(Integer bid);

    int insert(Bill record);

    int insertSelective(Bill record);

    Bill selectByPrimaryKey(Integer bid);

    int updateByPrimaryKeySelective(Bill record);

    int updateByPrimaryKey(Bill record);
}