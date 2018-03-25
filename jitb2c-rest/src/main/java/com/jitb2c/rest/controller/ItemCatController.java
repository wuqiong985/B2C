package com.jitb2c.rest.controller;

import com.jitb2c.common.utils.JsonUtils;
import com.jitb2c.rest.pojo.CatResult;
import com.jitb2c.rest.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品分类列表
 * @Author wuqiong6
 * @Date 2018/3/25 15:35
 */
@Controller
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping(value = "/itemcat/list",produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
    @ResponseBody
    public String getItemCatList(String callback){
        CatResult catResult = itemCatService.getItemCatList();

        //把pojo转换为字符串
        String json = JsonUtils.objectToJson(catResult);
        //回调方法名+json来让js调用并传递json数据，解决不能跨域问题
        String result = callback + "(" + json + ");";

        return result;
    }

//    @RequestMapping(value = "/itemcat/list")
//    @ResponseBody
//    public Object getItemCatList(String callback){
//        CatResult catResult = itemCatService.getItemCatList();
//        //使用Spring MVC提供的工具类MappingJacksonValue完成回调方法的封装
//        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(catResult);
//        mappingJacksonValue.getJsonpFunction();
//        return mappingJacksonValue;
//    }
}
