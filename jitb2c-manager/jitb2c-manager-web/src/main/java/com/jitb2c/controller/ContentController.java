package com.jitb2c.controller;

import com.jitb2c.common.pojo.EUDataGridResult;
import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.common.utils.JsonUtils;
import com.jitb2c.pojo.TbContent;
import com.jitb2c.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 内容管理
 * Created by wuqiong6 on 2018/3/26.
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    ContentService contentService;

    /**
     * 展示内容
     * @param categoryId 类目Id
     * @param page 页数
     * @param rows 记录条数
     * @return
     */
    @RequestMapping("/query/list")
    @ResponseBody
    public String getContentList(long categoryId, Integer page, Integer rows){
        EUDataGridResult result = contentService.getContentsById(categoryId,page,rows);
        String json = JsonUtils.objectToJson(result);
        return json;
    }

    @RequestMapping("/save")
    @ResponseBody
    public JitB2CResult saveContent(TbContent content){
        JitB2CResult result = contentService.saveContent(content);
        return result;
    }

}
