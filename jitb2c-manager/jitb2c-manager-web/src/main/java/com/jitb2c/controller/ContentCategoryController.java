package com.jitb2c.controller;

import com.jitb2c.common.pojo.EUTreeNode;
import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 内容分类Controller
 * @Author wuqiong6
 * @Date 2018/3/25 20:57
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    ContentCatService contentCatService;

    /**
     * 展示内容分类所有节点
     * @param parentId
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCatList(@RequestParam(value = "id",defaultValue = "0") long parentId){
        return contentCatService.getContentCatList(parentId);
    }

    @RequestMapping("/create")
    @ResponseBody
    public JitB2CResult createContentCatNode(long parentId,String name){
        return contentCatService.insertContentCat(parentId,name);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public JitB2CResult deleteContentCatNode(long parentId,long id){
        return contentCatService.deleteContentCat(parentId,id);
    }

}
