package com.gentel.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Area {
    private Integer areaId;
    private String areaName;
    private Integer priority;
    private Date creatTime;
    private Date updateTime;
}
