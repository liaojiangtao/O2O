package com.gentel.service;

import com.gentel.dto.ImageHolder;
import com.gentel.dto.ShopExecution;
import com.gentel.entity.Shop;
import com.gentel.exceptions.ShopOperationException;

public interface ShopService {

    /*添加店铺*/
    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

    /*
    * 通过店铺ID获取店铺信息
    *
    * @param shopId
    * @return
    * */
    Shop getByShopId(Long shopId);
}
