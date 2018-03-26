package com.jitb2c.portal.service.impl;

import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.common.utils.ExceptionUtil;
import com.jitb2c.common.utils.HttpClientUtil;
import com.jitb2c.common.utils.JsonUtils;
import com.jitb2c.pojo.TbContent;
import com.jitb2c.portal.service.ContentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 调用服务层服务，查询内容列表
 * @Author wuqiong6
 * @Date 2018/3/26 22:01
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Value("${REST_BASE_URL}")
    private String REST_BASE_URL;
    @Value("${REST_INDEX_AD_URL}")
    private String REST_INDEX_AD_URL;

    @Override
    public String getContentList() {

        //调用服务层的服务
        String result = HttpClientUtil.doGet(REST_BASE_URL+REST_INDEX_AD_URL);
        //将该字符串转成JitB2CResult
        try {
            JitB2CResult jitB2CResult = JitB2CResult.formatToList(result, TbContent.class);
            //取内容列表
            List<TbContent> list = (List<TbContent>) jitB2CResult.getData();
            List<Map> resultList = new ArrayList<>();
            //根据得到的内容列表创建一个jsp页面需要的pojo列表
            for (TbContent content:list){
                Map map = new HashMap<>();
                map.put("src", content.getPic());
                map.put("height", 240);
                map.put("width", 670);
                map.put("srcB", content.getPic2());
                map.put("widthB", 550);
                map.put("heightB", 240);
                map.put("href", content.getUrl());
                map.put("alt", content.getSubTitle());
                resultList.add(map);
            }
            return JsonUtils.objectToJson(resultList);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
