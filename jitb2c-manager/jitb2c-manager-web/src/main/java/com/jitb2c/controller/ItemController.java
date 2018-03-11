package com.jitb2c.controller;

import com.jitb2c.pojo.TbItem;
import com.jitb2c.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName:      ItemController
 * @Description:    商品管理Controller
 * @Author:         wuqiong6
 * @CreateDate:     2018/3/10 19:57
 * @Version:        1.0
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping ("/item/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId){
        TbItem tbItem = itemService.getItemById(itemId);
        return tbItem;
    }
}