package com.gentel.service.impl;

import com.gentel.dao.ShopDao;
import com.gentel.dto.ImageHolder;
import com.gentel.dto.ShopExecution;
import com.gentel.enmus.ShopStateEnum;
import com.gentel.entity.Shop;
import com.gentel.exceptions.ShopOperationException;
import com.gentel.service.ShopService;
import com.gentel.util.ImageUtil;
import com.gentel.util.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopDao shopDao;

    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
        //空值判断
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }

        try {
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setUpdateTime(new Date());

            Integer effectNum = shopDao.insertShop(shop);
            if (effectNum <= 0)
                throw new ShopOperationException("创建店铺失败");
            else{
                if (thumbnail.getImage() != null){
                    //存储图片
                    try{
                        addShopImg(shop, thumbnail);
                    }catch (Exception e){
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    //更新店铺的图片地址
                    effectNum = shopDao.updateShop(shop);
                    if (effectNum <= 0){
                        throw new ShopOperationException("更新图片地址失败");
                    }
                }
            }
        } catch (Exception e) {
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    @Transactional
    public ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException {
        if (shop == null || shop.getShopId() == null ){
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }else{
            // 1.判断是否需要处理图片
            try{
                if (thumbnail.getImage() != null && thumbnail.getImageName() != null && !"".equals(thumbnail.getImageName())){
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null){
                        ImageUtil.deleteFileOrFath(tempShop.getShopImg());
                    }
                    addShopImg(shop, thumbnail);
                }
                // 2.更新店铺信息
                shop.setUpdateTime(new Date());
                int effectNum = shopDao.updateShop(shop);
                if (effectNum <= 0){
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                }else{
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }
            }catch (Exception e){
                throw new ShopOperationException("modify err:"+e.getMessage());
            }
        }
    }

    private void addShopImg(Shop shop, ImageHolder thumbnail) {
        //获取shop图片目录的相对路径
        String dest = PathUtil.getShopImgPath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(thumbnail, dest);
        shop.setShopImg(shopImgAddr);
    }
}
