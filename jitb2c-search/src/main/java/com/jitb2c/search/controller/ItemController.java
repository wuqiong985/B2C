package com.jitb2c.search.controller;

import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.search.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 索引库维护
 * @Author wuqiong6
 * @Date 2018/3/28 2:22
 */
@Controller
@RequestMapping("/manager")
public class ItemController {

    @Autowired
    ItemService itemService;

    /**
     * 导入商品数据到索引库
     */
    @RequestMapping("/importall")
    @ResponseBody
    public JitB2CResult importAllItems() {
        JitB2CResult result = itemService.importAllItems();
        return result;
    }
}
