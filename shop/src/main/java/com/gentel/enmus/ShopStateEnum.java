package com.gentel.enmus;

import lombok.Data;

public enum ShopStateEnum {
    CHECK(0, "审核中"), OFFLINE(-1,"非法店铺"), SUCCESS(1,"操作成功"), NULL_SHOPID(-1002,"");
    private int state;
    private String stateInfo;
    private ShopStateEnum(int state, String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static ShopStateEnum stateOf(int state){
        for (ShopStateEnum stateEnum:values()){
            if (stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
