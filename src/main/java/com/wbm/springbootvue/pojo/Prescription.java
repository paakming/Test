package com.wbm.springbootvue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prescription implements Serializable {
    private Integer cfid;
    private Integer pid;
    private String jobid;
    private String message;
    private Date opentime;
    private String isDeleted;
    private String isCompleted;
    private Float totalPrice;
    private Doctor doctor;
    private Patient patient;

}