package com.wbm.springbootvue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor implements Serializable {
    private String jobid;
    private String name;
    private String sex;
    private Date birthday;
    private String age;
    private String department;
    private String identityCard;
    private String position;
    private String phone;
    private String isDeleted;

}