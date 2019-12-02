package com.example.mydemo.web;

import com.example.mydemo.entity.Area;
import com.example.mydemo.service.AreaService;
import com.example.mydemo.service.impl.AreaServiceImpl;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins="http://localhost:8080",maxAge = 3600)/*此注解需要添加在web接口文件中，不能添加到Application文件中*/
@RequestMapping("/superadmin")
public class AreaController {
    @Autowired
    AreaService areaService;

    @RequestMapping(value="/listarea",method = RequestMethod.GET)
    public Map<String,Object> listArea(){
        Map<String,Object> areaMap = new HashMap<String, Object>();
        List<Area> areaList = new ArrayList<Area>();
        areaList = areaService.getAreaList();
        areaMap.put("al",areaList);
        return areaMap;
    }

    //访问的连接为：http://localhost:8080/superadmin/getareabyid/?areaId=7
    @RequestMapping(value = "/getareabyid",method = RequestMethod.GET)
    private Map<String,Object> getAreaById(Integer areaId){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        //获取区域信息
        Area area = areaService.getAreaById(areaId);
        modelMap.put("area",area);
        return modelMap;
    }

    @RequestMapping(value = "/addarea",method = RequestMethod.POST)
    private Map<String,Object> addArea(@RequestBody Area area)
            throws JsonParseException, JsonMappingException, IOException {//这里为什么要抛出这么多异常
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("success",areaService.addArea(area));
        return modelMap;
    }

    @RequestMapping(value="/modifyarea",method = RequestMethod.POST)
    private Map<String ,Object> modifyArea(@RequestBody Area area){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("success",areaService.modifyArea(area));
        return modelMap;
    }

    //int areaId必须改为Integer areaId，否则，无法通讯，可能是因为int为值类型，不能为空，Integer为封装类型，可为空
    @RequestMapping(value="/deletearea",method=RequestMethod.GET)
    private Map<String,Object> deleteArea(Integer areaId){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("success",areaService.deleteArea(areaId));
        return modelMap;
    }

}
