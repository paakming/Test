package com.wbm.springbootvue.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer uid;
    private String jobid;
    private String phone;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String email;
    private String isManager;
    @JsonIgnore
    private String isDelete;
    private String token;
    private Doctor doctor;
}