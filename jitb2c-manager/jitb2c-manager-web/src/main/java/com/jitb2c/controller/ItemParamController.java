package com.jitb2c.controller;

import com.jitb2c.common.pojo.EUDataGridResult;
import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.pojo.TbItemParam;
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
    private ItemParamService itemParamService;

    /**
     * 分页查询商品规格模板
     * @param page 页数
     * @param rows 每页记录数
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public EUDataGridResult getItemParams(Integer page,Integer rows){
        EUDataGridResult result = itemParamService.getItems(page,rows);
        return result;
    }

    /**
     * 保存商品规格模板
     * @param itemCatId 商品分类id
     * @param paramData 从前台传来的商品规格json字符串
     * @return
     */
    @RequestMapping("/save/{itemCatId}")
    @ResponseBody
    public JitB2CResult insertItemParam(@PathVariable Long itemCatId,String paramData){
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(itemCatId);
        itemParam.setParamData(paramData);
        JitB2CResult result = itemParamService.insertItemParam(itemParam);
        return result;
    }

    /**
     * 根据cid查询商品规格模板
     * @param itemCatId 商品目录id
     * @return
     */
    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public JitB2CResult getItemParamByCid(@PathVariable Long itemCatId) {
        JitB2CResult result = itemParamService.getItemParamByCid(itemCatId);
        return result;
    }
}
