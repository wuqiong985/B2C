package com.jitb2c.service.impl;

import com.jitb2c.common.pojo.EUTreeNode;
import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.mapper.TbContentCategoryMapper;
import com.jitb2c.pojo.TbContentCategory;
import com.jitb2c.pojo.TbContentCategoryExample;
import com.jitb2c.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 内容分类管理Service
 * @Author wuqiong6
 * @Date 2018/3/25 21:06
 */
@Service
public class ContentCatServiceImpl implements ContentCatService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;


    /**
     * 查询商品分类内容
     * @param parentId 父节点Id
     * @return
     */
    @Override
    public List<EUTreeNode> getContentCatList(long parentId) {

        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        //执行查询
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<>();

        for (TbContentCategory contentCategory:list){
            //创建一个节点
            EUTreeNode node = new EUTreeNode();
            node.setId(contentCategory.getId());
            node.setText(contentCategory.getName());
            node.setState(contentCategory.getIsParent()?"closed":"open");
            resultList.add(node);
        }
        return resultList;
    }

    /**
     * 插入商品分类
     * @param parentId 父节点Id
     * @param name 名称
     * @return
     */
    @Override
    public JitB2CResult insertContentCat(long parentId, String name) {

        //创建一个pojo
        TbContentCategory contentCategory = new TbContentCategory();
        //需要返回主键Id,mapper里面进行修改com/jitb2c/mapper/TbContentCategoryMapper.xml
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        //需要同步更新父节点的状态

        //是否删除 1正常 2删除
        contentCategory.setStatus(1);
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //添加记录
        contentCategoryMapper.insert(contentCategory);
        //查看父节点的isParent是否为true，如果不是则改成是
        TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
        //判断是否为true
        if (!parentCat.getIsParent()){
            parentCat.setIsParent(true);
            //更新数据库中父节点的状态
            contentCategoryMapper.updateByPrimaryKey(parentCat);
        }
        //返回结果
        return JitB2CResult.ok(contentCategory);
    }

    @Override
    public JitB2CResult deleteContentCat(long parentId, long id) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        criteria.andIdEqualTo(id);
        contentCategoryMapper.deleteByExample(example);

        //查询被删除节点的父节点是否存在其他子节点，若不存在，则将其置为子节点
        TbContentCategoryExample example1 = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria1 = example1.createCriteria();
        criteria1.andParentIdEqualTo(parentId);
        Long count = contentCategoryMapper.countByExample(example1);
        if (count == 0){
            TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
            parentCat.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKey(parentCat);
        }

        return JitB2CResult.ok();
    }


}
