package com.gentel.service;

import com.gentel.dto.ImageHolder;
import com.gentel.dto.ShopExecution;
import com.gentel.entity.Shop;
import com.gentel.exceptions.ShopOperationException;

public interface ShopService {

    /*添加店铺*/
    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

    /*通过店铺ID查询店铺信息*/
    Shop getByShopId(long shopId);

    /*更新店铺信息 包括对图片的处理*/
    ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

    /*
    * 根据shopCondition分页返回相应店铺列表
    *
    * @param shopConditon
    * @param pageIndex
    * @param pageSize
    * @return
    * */
    ShopExecution getShopList(Shop shopCondition, Integer pageIndex, Integer pageSize);
}
