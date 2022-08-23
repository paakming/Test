package com.wbm.springbootvue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient implements Serializable {
    private Integer pid;
    private String identityCard;
    private String pname;
    private String sex;
    private Integer age;
    private Date birthday;
    private String phone;
    private String isDeleted;
}