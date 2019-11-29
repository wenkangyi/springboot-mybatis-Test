package com.example.mydemo.dao;
import static org.junit.Assert.assertEquals;
import com.example.mydemo.entity.Area;
import org.junit.Ignore;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;



@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {
    @Autowired
    private AreaDao areaDao;

    @Test
    public void queryArea() {
        List<Area> areaList = areaDao.queryArea();
        assertEquals(2,areaList.size());
    }

    @Test
    public void queryAreaById() {
        Area area = areaDao.queryAreaById(1);
        assertEquals("东苑",area.getAreaName());
    }

    @Test
    public void insertArea() {
        Area area = new Area();
        area.setAreaName("西苑5");
        area.setPriority(8);
        area.setCreateTime(new Date());
        area.setLastEditTime(new Date());
        int effectedNum = areaDao.insertArea(area);
        assertEquals(1,effectedNum);

        //测试行数是否为3
        List<Area> areaList = areaDao.queryArea();
        assertEquals(5,areaList.size());
    }

    @Test
    public void updateArea() {
        Area area = new Area();
        area.setAreaId(1);
        area.setAreaName("更改的东苑");
        area.setPriority(8);
        area.setLastEditTime(new Date());

        int effectedNum = areaDao.updateArea(area);
        assertEquals(1,effectedNum);
    }

    @Test
    public void deleteArea() {
        int effectedNum = areaDao.deleteArea(3);
        assertEquals(1,effectedNum);
    }
}