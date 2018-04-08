package com.jitb2c.rest.controller;

import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.rest.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 展示商品详情页面controller
 * @Author wuqiong6
 * @Date 2018/4/6 21:28
 */
@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/info/{itemId}")
    @ResponseBody
    public JitB2CResult getItemBaseInfo(@PathVariable long itemId){
        JitB2CResult result = itemService.getItemBaseInfo(itemId);
        return result;
    }

    @RequestMapping("/desc/{itemId}")
    @ResponseBody
    public JitB2CResult getItemDesc(@PathVariable long itemId){
        JitB2CResult result = itemService.getItemDesc(itemId);
        return result;
    }

    @RequestMapping("/param/{itemId}")
    @ResponseBody
    public JitB2CResult getItemParam(@PathVariable long itemId){
        JitB2CResult result = itemService.getItemParam(itemId);
        return result;
    }
}
