package com.gentel.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gentel.dao.AreaDao;
import com.gentel.entity.Area;
import com.gentel.service.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    private AreaDao areaDao;

    @Override
    @Transactional
    public List<Area> getAreaList() {

        List<Area> areaList = null;

        ObjectMapper mapper = new ObjectMapper();

        areaList = areaDao.queryArea();

        String jsonString;

        try{
            jsonString = mapper.writeValueAsString(areaList);
        }catch (JsonProcessingException e){
            e.printStackTrace();
            log.error(e.getMessage());
            throw new AreaOperationException(e.getMessage());
        }
        return null;
    }
}
