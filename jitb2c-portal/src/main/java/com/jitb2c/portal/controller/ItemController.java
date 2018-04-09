package com.jitb2c.portal.controller;

import com.jitb2c.portal.pojo.ItemInfo;
import com.jitb2c.portal.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;

/**
 * 商品详情页面展示Controller
 * @Author wuqiong6
 * @Date 2018/4/8 16:28
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/{itemId}")
    public String showItem(@PathVariable long itemId,Model model){
        ItemInfo item = itemService.getItemById(itemId);
        model.addAttribute("item",item);
        return "item";
    }

    @RequestMapping(value = "/item/desc/{itemId}",produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemDesc(@PathVariable long itemId){
        String string = itemService.getItemDescById(itemId);
        return string;
    }

    @RequestMapping(value = "/item/param/{itemId}",produces = MediaType.TEXT_HTML_VALUE+";charset=utf-8")
    @ResponseBody
    public String getItemParam(@PathVariable long itemId){
        String string = itemService.getItemParam(itemId);
        return string;
    }
}
