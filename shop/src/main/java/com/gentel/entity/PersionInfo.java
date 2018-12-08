package com.gentel.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PersionInfo {
    private Long userId;
    private String name;
    private String protfileImg;
    private String email;
    private String gender;
    private Integer enableStatus;
    private Integer userType;
    private Data creatTime;
    private Data updateTime;
}
