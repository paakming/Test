package com.wbm.springbootvue.service;

import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.pojo.Registration;

public interface RegistrationService {
    int deleteByPrimaryKey(Short rid);

    int insert(Registration record);

    Registration selectByPrimaryKey(Short rid);

    int updateByPrimaryKeySelective(Registration record);

    PageInfo<Registration> selectByjobid(String jobid,Integer pageNum, Integer pageSize);

}
