package com.wbm.springbootvue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bill implements Serializable {
    private Integer bid;
    private Integer pid;
    private Short rid;
    private String jobid;
    private Integer cfid;
    private Date finishedtime;
    private String isCompleted;
    private String isDeleted;

}