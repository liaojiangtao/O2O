package com.gentel.dao;

import com.gentel.dto.ShopExecution;
import com.gentel.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /*
     * 分页查询店铺，可输入条件有：店铺名（模糊），店铺状态，店铺类别，区域ID，owner
     *
     * @param rowIndex 从第几行开始取数据
     * @param pagesize 返回条数
     * @return
     * */
    List<Shop> queryShopList(@Param("shopCondition") Shop shopCondition, @Param("rowIndex") Integer rowIndex,
                             @Param("pageSize") Integer pageSize);

    /*
    * 返回queryShopList总数
    *
    * @param shopCondition
    * @return
    * */
    int queryShopCount(@Param("shopCondition")Shop shopCondition);
}
