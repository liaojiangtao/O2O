package com.gentel.service;

import com.gentel.entity.Area;

import java.util.List;

public interface AreaService {
    public static final String AREALISTKEY = "arealist";

    /*
    * 获取区域列表
    *
    * @return
    * */
    List<Area> getAreaList();
}
