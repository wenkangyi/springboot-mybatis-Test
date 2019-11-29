package com.example.mydemo.service.impl;

import com.example.mydemo.dao.AreaDao;
import com.example.mydemo.entity.Area;
import com.example.mydemo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaDao areaDao;


    @Override
    public List<Area> getAreaList() {
        return areaDao.queryArea();
    }

    @Override
    public Area getAreaById(Integer areaId) {
        if(areaId == null) return null;
        return areaDao.queryAreaById(areaId);
    }

    @Transactional //这个为添加事务注释，有多个操作都需要开启事务，这样可以保证数据的一致性
    @Override
    public boolean addArea(Area area) {
        if(area.getAreaName()==null || "".equals(area.getAreaName())) return false;
        area.setCreateTime(new Date());
        area.setLastEditTime(new Date());
        int effectedNum = areaDao.insertArea(area);
        if(effectedNum > 0) return true;
        return false;
    }

    @Transactional
    @Override
    public boolean modifyArea(Area area) {
        if(area.getAreaName() == null || "".equals(area.getAreaName())) return false;
        area.setLastEditTime(new Date());
        int effectedNum = areaDao.updateArea(area);
        if(effectedNum > 0) return true;
        return false;
    }

    @Transactional
    @Override
    public boolean deleteArea(int areaId) {
        if(areaId <= 0) return false;
        int effectedNum = areaDao.deleteArea(areaId);
        if(effectedNum > 0) return true;
        return false;
    }
}
