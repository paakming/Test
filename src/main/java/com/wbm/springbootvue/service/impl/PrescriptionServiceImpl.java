package com.wbm.springbootvue.service.impl;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wbm.springbootvue.common.ResultCode;
import com.wbm.springbootvue.exception.ServiceException;
import com.wbm.springbootvue.mapper.*;
import com.wbm.springbootvue.pojo.*;
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
    @Autowired
    DoctorMapper doctorMapper;
    @Autowired
    PatientMapper patientMapper;

    @Override
    public PageInfo<Prescription> allPrescription(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Prescription> list = prescriptionMapper.allPrescription();
        PageInfo<Prescription> prescriptionPageInfo =new PageInfo<>(list);
        return prescriptionPageInfo;
    }

    @Override
    public List<Prescription> allPrescription() {
        return prescriptionMapper.allPrescription();
    }

    @Override
    public Integer deleteByPrimaryKey(Integer cfid) {
        return prescriptionMapper.deleteByPrimaryKey(cfid);
    }

    @Override
    public Integer insert(String pname,String name, String message,String drugname,String usage,String quantity) {
        Patient patient;
        try {
            patient  = patientMapper.selectByPrimaryName(pname);
        }catch (Exception e){
            throw new ServiceException(ResultCode.CODE_401,"系统错误");
        }
        if (patient == null){
            throw new ServiceException(ResultCode.CODE_500,"患者姓名错误");
        }
        Doctor doctor;
        try {
            doctor = doctorMapper.selectByName(name);
        }catch (Exception e){
            throw new ServiceException(ResultCode.CODE_401,"系统错误");
        }
        if (doctor == null){
            throw new ServiceException(ResultCode.CODE_500,"医师姓名错误");
        }
        Medicine medicine;
        try {
            medicine = medicineMapper.selectByName(drugname);
        }catch (Exception e){
            throw new ServiceException(ResultCode.CODE_401,"系统错误");
        }
        if (medicine == null){
            throw new ServiceException(ResultCode.CODE_500,"药品不存在");
        }
        Prescription prescription = new Prescription();
        prescription.setJobid(doctor.getJobid());
        prescription.setPid(patient.getPid());
        prescription.setMessage(message);
        prescription.setOpentime(DateUtil.date(System.currentTimeMillis()));
        prescription.setTotalPrice(medicine.getDrugprice());
        prescription.setIsDeleted("0");
        prescription.setIsCompleted("0");
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
            return r;
        }
        return 0;
    }

    @Override
    public List<Prescription> selectByPrimaryKey(Integer cfid) {
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
        Medicine medicine;
        try {
           medicine = medicineMapper.selectByName(drugname);
        }catch (Exception e){
            throw new ServiceException(ResultCode.CODE_401,"系统错误");
        }
        if (medicine == null){
            throw new ServiceException(ResultCode.CODE_500,"药品不存在");
        }
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
