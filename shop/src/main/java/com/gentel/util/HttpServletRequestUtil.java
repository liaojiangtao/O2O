package com.gentel.util;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil {
    public static Integer getInt(HttpServletRequest request, String key){
        try{
            return Integer.decode(request.getParameter(key));
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
    }

    public static Long getLong(HttpServletRequest request, String key){
        try{
            return Long.decode(request.getParameter(key));
        }catch (Exception e){
            e.printStackTrace();
            return -1L;
        }
    }

    public static Double getDouble(HttpServletRequest request, String key){
        try{
            return Double.valueOf(request.getParameter(key));
        }catch (Exception e){
            e.printStackTrace();
            return -1d;
        }
    }

    public static Boolean getBoolean(HttpServletRequest request, String key){
        try{
            return Boolean.valueOf(request.getParameter(key));
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public static String getString(HttpServletRequest request, String key){
        try{
            String result = request.getParameter(key);
            if (null != result){
                result = result.trim();
            }

            if ("".equals(result)){
                return null;
            }

            return result;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
