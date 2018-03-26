package com.jitb2c.rest.controller;

import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.common.utils.ExceptionUtil;
import com.jitb2c.pojo.TbContent;
import com.jitb2c.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 首页内容管理Controller (发布服务)
 * Created by wuqiong6 on 2018/3/26.
 */
@Controller
@RequestMapping("/content")
public class ContentController {

    @Autowired
    ContentService contentService;

    /**
     * 根据内容分类Id查询内容列表，因为是restful风格，所以contentCatId从url中取
     * @param contentCategoryId 内容分类Id
     * @return
     */
    @RequestMapping("/list/{contentCategoryId}")
    @ResponseBody
    public JitB2CResult getContentList(@PathVariable Long contentCategoryId){
        try {
            List<TbContent> list = contentService.getTbContentList(contentCategoryId);
            return JitB2CResult.ok(list);
        }catch (Exception e){
            e.printStackTrace();
            //使用了处理异常的工具类
            return JitB2CResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }
}
