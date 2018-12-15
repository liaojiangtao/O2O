package com.gentel.util;

public class PathUtil {
    private static String seperator = System.getProperty("file.separator");

    public static String getImgBasePath(){
        String os = System.getProperty("os.name");
        String basePath = "";

        if (os.toLowerCase().startsWith("win")){
            basePath = "D:/shop/image";
        }else{
            basePath = "/home/shop/image";
        }

        basePath = basePath.replace("/", seperator);

        return basePath;
    }

    public static String getShopImgPath(long shopId){
        String imagePath = "/upload/images/item/shop" + shopId + "/";
        return imagePath.replace("/", seperator);
    }
}
