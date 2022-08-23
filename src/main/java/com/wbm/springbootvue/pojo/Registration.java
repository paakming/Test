package com.wbm.springbootvue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Registration implements Serializable {
    private Integer rid;
    private Integer pid;
    private String jobid;
    private Float price;
    private Date visitTime;
    private String isCompleted;
    private Integer cfid;
    private String isDeleted;
    private Patient patient;
}