package com.gentel.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Product {
    private Long productId;
    private String productName;
    private String productDesc;
    private String imgAddr;
    private String noramlPrice;
    private String promotionPrice;
    private Integer priority;
    private Date createTime;
    private Date updateTime;
    private Integer enableStatus;
    private List<ProductImg> productImgList;
    private ProductCategory productCategory;
    private Shop shop;
}
