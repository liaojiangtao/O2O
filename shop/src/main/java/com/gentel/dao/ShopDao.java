package com.gentel.dao;

import com.gentel.entity.Shop;

public interface ShopDao {
    /*
    * 新增店铺
    * @param shop
    * @return
    * */
    int insertShop(Shop shop);

    /*
     * 更新店铺
     * @param shop
     * @return
     * */
    int updateShop(Shop shop);

    /*
    * 通过shop id 查询店铺
    * @parm shopId
    * @return shop
    * */
    Shop queryByShopId(long shopId);
}
