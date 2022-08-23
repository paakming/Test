package com.wbm.springbootvue.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.mapper.DetailMapper;
import com.wbm.springbootvue.mapper.MedicineMapper;
import com.wbm.springbootvue.mapper.PrescriptionMapper;
import com.wbm.springbootvue.mapper.RegistrationMapper;
import com.wbm.springbootvue.pojo.Detail;
import com.wbm.springbootvue.pojo.Medicine;
import com.wbm.springbootvue.pojo.Prescription;
import com.wbm.springbootvue.pojo.Registration;
import com.wbm.springbootvue.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    PrescriptionMapper prescriptionMapper;
    @Autowired
    DetailMapper detailMapper;
    @Autowired
    MedicineMapper medicineMapper;
    @Autowired
    RegistrationMapper registrationMapper;

    @Override
    public PageInfo<Prescription> allPrescription(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Prescription> list = prescriptionMapper.allPrescription();
        PageInfo<Prescription> prescriptionPageInfo =new PageInfo<>(list);
        return prescriptionPageInfo;
    }

    @Override
    public Integer deleteByPrimaryKey(Integer cfid) {
        return prescriptionMapper.deleteByPrimaryKey(cfid);
    }

    @Override
    public Integer insert(Prescription prescription) {
        prescription.setIsDeleted("0");
        return prescriptionMapper.insert(prescription);
    }

    @Override
    public Prescription selectByPrimaryKey(Integer cfid) {
        return prescriptionMapper.selectByPrimaryKey(cfid);
    }

    @Override
    public Integer updateByPrimaryKeySelective(Prescription prescription) {
        return prescriptionMapper.updateByPrimaryKeySelective(prescription);
    }

    @Override
    public Integer deleteBycfids(List<Integer> ids) {
        return prescriptionMapper.deleteBycfids(ids);
    }

    @Override
    public Integer newPrescription(Integer pid, String jobid, String message, String drugname, String usage, String quantity,Integer rid) {
        Medicine medicine = medicineMapper.selectByName(drugname);
        Prescription prescription = new Prescription();
        prescription.setOpentime(DateUtil.date(System.currentTimeMillis()));
        prescription.setJobid(jobid);
        prescription.setPid(pid);
        prescription.setMessage(message);
        prescription.setIsDeleted("0");
        prescription.setIsCompleted("0");
        prescription.setTotalPrice(medicine.getDrugprice());
        int res = prescriptionMapper.insert(prescription);
        int cfid = prescription.getCfid();
        if (1==res){
            Detail detail = new Detail();
            detail.setCfid(cfid);
            detail.setQuantity(quantity);
            detail.setMid(medicine.getMid());
            detail.setUsage(usage);
            detail.setIsDeleted("0");
            int r = detailMapper.insert(detail);
            if (1==r){
                Registration registration = new Registration();
                registration.setCfid(cfid);
                registration.setRid(rid);
                registrationMapper.updateByPrimaryKeySelective(registration);
                return cfid;
            }
        }
        return 0;
    }
}
