package com.jiang.demo.web;

import com.jiang.demo.service.AreaService;
import com.jiang.demo.vo.AreaVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/superadmin")
public class AreaController {
    @Autowired
    private AreaService service;

    @RequestMapping(value = "/listArea",method = RequestMethod.GET)
    private Map<String,Object> listArea(){
        Map<String,Object> modeMap = new HashMap<String, Object>();
        List<AreaVO> list = service.queryArea();
        modeMap.put("areaList",list);
        return  modeMap;
    }

    @RequestMapping(value = "/getAreaById",method = RequestMethod.GET)
    private  Map<String,Object> getAreaById(Integer areaId){
        Map<String,Object> modeMap = new HashMap<String, Object>();
        AreaVO area = service.queryAreaById(areaId);
        modeMap.put("area",area);
        return  modeMap;
    }
    @RequestMapping(value = "/addarea",method = RequestMethod.POST)
    private Map<String,Object> addArea(@RequestBody AreaVO area){
        Map<String,Object> modeMap = new HashMap<String,Object>();
        modeMap.put("success",service.insertArea(area));
        return modeMap;
    }
    @RequestMapping(value = "/modifyArea",method = RequestMethod.POST)
    private Map<String,Object> modifyArea(@RequestBody AreaVO area){
        Map<String,Object> modeMap = new HashMap<String,Object>();
        modeMap.put("success",service.updateArea(area));
        return modeMap;
    }
    @RequestMapping(value = "/removearea",method = RequestMethod.GET)
    private Map<String,Object> removArea(Integer areaId){
        Map<String,Object> modeMap = new HashMap<String,Object>();
        System.out.print(areaId);
        modeMap.put("success",service.deleteArea(areaId));
        return modeMap;
    }
}
