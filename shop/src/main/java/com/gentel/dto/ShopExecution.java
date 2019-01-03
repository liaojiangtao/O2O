package com.gentel.dto;

import com.gentel.enmus.ShopStateEnum;
import com.gentel.entity.Shop;
import lombok.Data;

import java.util.List;

@Data
public class ShopExecution {
    //结果状态
    private Integer state;
    //状态标识
    private String stateInfo;
    //店铺数量
    private Integer count;
    //操作的shop（增删改店铺的时候用到）
    private Shop shop;
    //shop列表（查询店铺列表的时候用到）
    private List<Shop> shopList;

    public ShopExecution(ShopStateEnum stateEnum){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
    }

    public ShopExecution(ShopStateEnum stateEnum, Shop shop){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.shop = shop;
    }
}
