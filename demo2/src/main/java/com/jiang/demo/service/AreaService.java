package com.jiang.demo.service;

import com.jiang.demo.vo.AreaVO;

import java.util.List;

public interface AreaService {
    List<AreaVO> queryArea();

    AreaVO queryAreaById(int areaId);

    boolean insertArea(AreaVO areaVO);

    boolean updateArea(AreaVO areaVO);

    boolean deleteArea(int areaId);
}
