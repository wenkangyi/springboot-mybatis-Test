package com.example.mydemo.service;

import com.example.mydemo.entity.Area;

import java.util.List;

public interface AreaService {
    List<Area> getAreaList();

    Area getAreaById(Integer areaId);

    boolean addArea(Area area);

    boolean modifyArea(Area area);

    boolean deleteArea(int areaId);
}
