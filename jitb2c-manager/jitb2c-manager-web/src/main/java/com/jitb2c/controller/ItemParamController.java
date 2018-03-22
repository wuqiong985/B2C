package com.jitb2c.controller;

import com.jitb2c.common.pojo.EUDataGridResult;
import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 商品规格参数模板管理Controller
 * @Author wuqiong6
 * @Date 2018/3/22 22:57
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Autowired
    ItemParamService itemParamService;

    @RequestMapping("/list")
    @ResponseBody
    EUDataGridResult getItemParams(Integer page,Integer rows){
        EUDataGridResult result = itemParamService.getItems(page,rows);
        return result;
    }


    /**
     * 根据cid查询商品分组
     * @param itemCatId
     * @return
     */
    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public JitB2CResult getItemParamByCid(@PathVariable Long itemCatId) {
        JitB2CResult result = itemParamService.getItemParamByCid(itemCatId);
        return result;
    }
}
