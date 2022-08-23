package com.wbm.springbootvue.mapper;

import com.wbm.springbootvue.pojo.Registration;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationMapper {
    int deleteByPrimaryKey(Short rid);

    int insert(Registration record);

    Registration selectByPrimaryKey(Short rid);

    int updateByPrimaryKeySelective(Registration record);

    List<Registration> selectByjobid(@Param("jobid") String jobid);
}