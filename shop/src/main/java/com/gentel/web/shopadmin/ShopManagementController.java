package com.gentel.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gentel.dto.ImageHolder;
import com.gentel.dto.ShopExecution;
import com.gentel.enmus.ShopStateEnum;
import com.gentel.entity.Area;
import com.gentel.entity.PersonInfo;
import com.gentel.entity.Shop;
import com.gentel.entity.ShopCategory;
import com.gentel.exceptions.ShopOperationException;
import com.gentel.service.AreaService;
import com.gentel.service.ShopCategoryService;
import com.gentel.service.ShopService;
import com.gentel.util.CodeUtil;
import com.gentel.util.HttpServletRequestUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {
    @Autowired
    private ShopCategoryService shopCategoryService;
    @Autowired
    private AreaService areaService;
    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "/registershop", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> registerShop(HttpServletRequest request) {
        Map<String, Object> modeMap = new HashMap<String, Object>();

        if (!CodeUtil.checkVerifyCode(request)) {
            modeMap.put("success", false);
            modeMap.put("errMsg", "输入了错误的验证码");
//            return modeMap;
        }

        //1.接收并转化相应的参数，包括店铺信息以及图片信息
        String shopStr = HttpServletRequestUtil.getString(request, "shopStr");
        ObjectMapper mapper = new ObjectMapper();
        Shop shop = null;
        try {
            shop = mapper.readValue(shopStr, Shop.class);
        } catch (Exception e) {
            modeMap.put("success", false);
            modeMap.put("errMsg", e.getMessage());
            return modeMap;
        }

        CommonsMultipartFile shopImg = null;
        CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        if (commonsMultipartResolver.isMultipart(request)) {
            MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
            shopImg = (CommonsMultipartFile) multipartHttpServletRequest.getFile("shopImg");
        } else {
            modeMap.put("success", false);
            modeMap.put("errMsg", "上传图片不能为空");
            return modeMap;
        }

        //2.店铺注册
        if (shop != null && shopImg != null) {
            PersonInfo owner = (PersonInfo) request.getSession().getAttribute("user");
            shop.setOwner(owner);
            ShopExecution se;
            try {
                ImageHolder imageHolder = new ImageHolder(shopImg.getOriginalFilename(), shopImg.getInputStream());
                se = shopService.addShop(shop, imageHolder);
                if (se.getState() == ShopStateEnum.CHECK.getState()) {
                    modeMap.put("success", true);
                    //该用户可以操作的店铺列表
                    List<Shop> shopList = (List<Shop>) request.getSession().getAttribute("shopList");
                    if (shopList == null || shopList.size() == 0) {
                        shopList = new ArrayList<Shop>();
                    }
                    shopList.add(se.getShop());
                    request.getSession().setAttribute("shopList", shopList);
                } else {
                    modeMap.put("success", false);
                    modeMap.put("errMsg", se.getStateInfo());
                }
            } catch (ShopOperationException e) {
                modeMap.put("success", false);
                modeMap.put("errMsg", e.getMessage());
            } catch (IOException e) {
                modeMap.put("success", false);
                modeMap.put("errMsg", e.getMessage());
            }
            return modeMap;
        }else{
            modeMap.put("success",false);
            modeMap.put("errMsg","请输入店铺信息");
            return modeMap;
        }
    }

    @RequestMapping(value = "getshopinitinfo", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> getShopInitInfo() {
        Map<String, Object> modeMap = new HashMap<String, Object>();
        List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
        List<Area> areaList = new ArrayList<Area>();

        try {
            shopCategoryList = shopCategoryService.getShopCategoryList(new ShopCategory());
            areaList = areaService.getAreaList();
            modeMap.put("shopCategoryList", shopCategoryList);
            modeMap.put("areaList", areaList);
            modeMap.put("success", true);
        } catch (Exception e) {
            modeMap.put("succsee", false);
            modeMap.put("errmsg", e.getMessage());
        }

        return modeMap;
    }
}
