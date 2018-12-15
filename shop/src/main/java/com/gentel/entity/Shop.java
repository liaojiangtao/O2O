package com.gentel.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Shop {
    private Long shopId;
    private String shopName;
    private String shopDesc;
    private String shopAddr;
    private String phone;
    private String shopImg;
    private Integer priority;
    private Date createTime;
    private Date updateTime;
    private Integer enableStatus;
    private String advice;

    private Area area;
    private PersonInfo owner;
    private ShopCategory shopCategory;
}
