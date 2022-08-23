package com.wbm.springbootvue.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.mapper.RegistrationMapper;
import com.wbm.springbootvue.pojo.Registration;
import com.wbm.springbootvue.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {
    @Autowired
    RegistrationMapper registrationMapper;

    @Override
    public int deleteByPrimaryKey(Short rid) {
        return 0;
    }

    @Override
    public int insert(Registration record) {
        return 0;
    }

    @Override
    public Registration selectByPrimaryKey(Short rid) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(Registration record) {
        record.setIsCompleted("1");
        return registrationMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public PageInfo<Registration> selectByjobid(String jobid,Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Registration> list = registrationMapper.selectByjobid(jobid);
        PageInfo<Registration> prescriptionPageInfo =new PageInfo<>(list);
        return prescriptionPageInfo;
    }
}
