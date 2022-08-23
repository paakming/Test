package com.wbm.springbootvue.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Medicine {
    private String mid;

    private String drugname;

    private String quantity;

    private String type;

    private Date entertime;

    private Float drugprice;

}