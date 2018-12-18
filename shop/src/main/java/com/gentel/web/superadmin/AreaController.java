package com.gentel.web.superadmin;

import com.gentel.entity.Area;
import com.gentel.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/superadmin")
public class AreaController {
    @Autowired
    private AreaService areaService;

    @RequestMapping(value = "/listarea", method = RequestMethod.GET)
    @ResponseBody
    private Map<String, Object> listArea(){
        Map<String, Object> modeMap = new HashMap<String, Object>();
        List<Area> list = new ArrayList<Area>();
        try{
            list = areaService.getAreaList();
            modeMap.put("rows", list);
            modeMap.put("total",list.size());
            modeMap.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            modeMap.put("success", false);
            modeMap.put("errMsg", e.toString());
        }
        return modeMap;
    }
}
