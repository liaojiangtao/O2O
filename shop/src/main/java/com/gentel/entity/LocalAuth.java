package com.gentel.entity;

import lombok.Data;

import java.util.Date;

@Data
public class LocalAuth {
    private Long localAuthId;
    private String userName;
    private String password;
    private Date createTime;
    private Date updateTime;
    private PersonInfo personInfo;
}
