package com.wbm.springbootvue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detail implements Serializable {
    private Integer did;

    private Integer cfid;

    private String mid;

    private String quantity;

    private String usage;

    private String isDeleted;

    private Medicine medicine;


}