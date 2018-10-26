package com.jiang.demo.service.impl;

import com.jiang.demo.vo.AreaVO;
import com.jiang.demo.dao.AreaDao;
import com.jiang.demo.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Override
    public List<AreaVO> queryArea() {
        return areaDao.queryArea();
    }

    @Override
    public AreaVO queryAreaById(int areaId) {
        return areaDao.queryAreaById(areaId);
    }

    @Transactional
    @Override
    public boolean insertArea(AreaVO areaVO) {
        if(areaVO.getAreaName()!=null && !"".equals(areaVO.getAreaName())){
            areaVO.setCreateDate(new Date());
            areaVO.setLastUpdateDate(new Date());
            try{
                int effectedNum = areaDao.insertArea(areaVO);
                if(effectedNum>0){
                    return true;
                }else {
                    throw new RuntimeException("插入区域信息失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("插入区域信息失败！"+e.getMessage());
            }
        }else{
            throw new RuntimeException("插入区域信息失败！");
        }
    }
    @Transactional
    @Override
    public boolean updateArea(AreaVO areaVO) {
        if(areaVO.getAreaId()!=null && areaVO.getAreaId()>0){
            areaVO.setLastUpdateDate(new Date());
            try{
                int effectedNum = areaDao.updateArea(areaVO);
                if(effectedNum>0){
                    return true;
                }else{
                    throw new RuntimeException("更新区域信息失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("更新区域信息失败！"+e.getMessage());
            }
        }else{
            throw new RuntimeException("更新区域信息失败！");
        }
    }
    @Transactional
    @Override
    public boolean deleteArea(int areaId) {
        if(areaId>0){
            try{
                int effectedNum = areaDao.deleteArea(areaId);
                if(effectedNum>0){
                    return true;
                }else{
                    throw new RuntimeException("删除区域信息失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("删除区域信息失败！"+e.getMessage());
            }
        }else{
            throw new RuntimeException("删除区域信息失败！");
        }
    }
}