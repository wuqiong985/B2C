package com.jitb2c.rest.service.impl;

import com.jitb2c.mapper.TbContentMapper;
import com.jitb2c.pojo.TbContent;
import com.jitb2c.pojo.TbContentExample;
import com.jitb2c.rest.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 内容管理Service
 * Created by wuqiong6 on 2018/3/26.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    TbContentMapper contentMapper;

    @Override
    public List<TbContent> getTbContentList(long contentCatId) {
        //创建查询条件
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCatId);

        //查询结果
        List<TbContent> list = contentMapper.selectByExample(example);

        return list;
    }
}
