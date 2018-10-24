package com.jiang.demo.dao;

import com.jiang.demo.vo.AreaVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {
    @Autowired
    private AreaDao areaDao;
    @Test
    public void queryArea() {
        List<AreaVO> areaList = areaDao.queryArea();
        assertEquals(2,areaList.size());
    }

    @Test
    public void queryAreaById() {
        AreaVO area  = areaDao.queryAreaById(2);
        assertEquals("武汉",area.getAreaName());
    }

    @Test
    public void insertArea() {
        AreaVO area = new AreaVO();
        area.setAreaName("武汉");
        area.setAreaIndex(4);
        int effectedNum = areaDao.insertArea(area);
        assertEquals(1,effectedNum);
    }

    @Test
    public void updateArea() {
        AreaVO area = new AreaVO();
        area.setAreaId(3);
        area.setAreaIndex(2);
        area.setLastUpdateDate(new Date());
        int effectedNum = areaDao.updateArea(area);
        assertEquals(1,effectedNum);
    }

    @Test
    public void deleteArea() {
        int effectedNum = areaDao.deleteArea(5);
        assertEquals(1,effectedNum);
    }
}