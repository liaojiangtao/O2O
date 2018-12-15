package com.gentel.entity;

import lombok.Data;

import java.util.Date;

@Data
public class ProductImg {
    private Long productImgId;
    private String imgAddr;
    private String imgDesc;
    private Integer priority;
    private Date createTime;
    private Long productId;
}
