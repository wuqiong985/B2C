package com.jitb2c.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jitb2c.common.pojo.EUDataGridResult;
import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.mapper.TbContentMapper;
import com.jitb2c.pojo.TbContent;
import com.jitb2c.pojo.TbContentExample;
import com.jitb2c.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 内容管理service
 * Created by wuqiong6 on 2018/3/26.
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    TbContentMapper contentMapper;

    /**
     * 根据分类Id和分页参数查询内容
     * @param categoryId 分类id
     * @param page 页数
     * @param rows 每页显示数目
     * @return
     */
    @Override
    public EUDataGridResult getContentsById(long categoryId, Integer page, Integer rows) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        //使用分页
        PageHelper.startPage(page,rows);
        List<TbContent> list = contentMapper.selectByExample(example);
        //封装结果
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);
        //取总条数
        PageInfo<TbContent> pageInfo = new PageInfo<>(list);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 保存广告内容
     * @param content 要保存的内容
     * @return
     */
    @Override
    public JitB2CResult saveContent(TbContent content) {
        content.setCreated(new Date());
        content.setUpdated(new Date());

        contentMapper.insert(content);
        return JitB2CResult.ok();
    }
}
