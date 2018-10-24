package com.jiang.demo.dao;

import com.jiang.demo.vo.AreaVO;

import java.util.List;

public interface AreaDao {

    List<AreaVO> queryArea();

    AreaVO queryAreaById(int areaId);

    int insertArea(AreaVO areaVO);

    int updateArea(AreaVO areaVO);

    int deleteArea(int areaId);


}
