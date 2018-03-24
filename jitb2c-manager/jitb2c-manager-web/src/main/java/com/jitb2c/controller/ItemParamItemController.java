package com.jitb2c.controller;

import com.jitb2c.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 展示商品规格参数Controller
 * @Author wuqiong6
 * @Date 2018/3/24 17:47
 */
@Controller
public class ItemParamItemController {

    @Autowired
    ItemParamItemService itemParamItemService;

    @RequestMapping("/showItem/{itemId}")
    public String showItemParam(@PathVariable long itemId,Model model){
        String string  = itemParamItemService.getItemParamItemByItemId(itemId);
        model.addAttribute("itemParam",string);
        return "item";
    }
}
