package com.jitb2c.controller;

import com.jitb2c.common.pojo.EUTreeNode;
import com.jitb2c.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品分类管理Controller
 * Created by wuqiong6 on 2018/3/12.
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    /**
     * 前台传入节点Id,调用service查出子节点内容并展示
     * @param parentId
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getCatList(@RequestParam(value = "id",defaultValue = "0") long parentId){
        List<EUTreeNode> list = itemCatService.getCatList(parentId);
        return list;
    }



}
