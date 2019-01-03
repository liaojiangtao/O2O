package com.gentel.service;

import com.gentel.dto.ImageHolder;
import com.gentel.dto.ShopExecution;
import com.gentel.entity.Shop;
import com.gentel.exceptions.ShopOperationException;

public interface ShopService {

    /*添加店铺*/
    ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}
