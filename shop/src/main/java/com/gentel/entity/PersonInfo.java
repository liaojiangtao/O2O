package com.gentel.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PersonInfo {
    private Long userId;
    private String name;
    private String profileImg;
    private String email;
    private String gender;
    private Integer enableStatus;
    private Integer userType;
    private Date createTime;
    private Date updateTime;
}
