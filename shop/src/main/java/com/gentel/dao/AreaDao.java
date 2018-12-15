package com.gentel.dao;

import com.gentel.entity.Area;

import java.util.List;

public interface AreaDao {
    /*
     * 查询区域
     * @param shop
     * @return
     * */
    List <Area> queryArea();
}
