package com.jitb2c.service.impl;

import com.jitb2c.common.pojo.EUTreeNode;
import com.jitb2c.mapper.TbItemCatMapper;
import com.jitb2c.pojo.TbItemCat;
import com.jitb2c.pojo.TbItemCatExample;
import com.jitb2c.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品分类管理
 * Created by wuqiong6 on 2018/3/12.
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<EUTreeNode> getCatList(long parentId) {

        //创建查询条件
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);

        //根据条件查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        List<EUTreeNode> result = new ArrayList<>();
        //把列表转换成treeNodeList
        for (TbItemCat itemCat:list){
            EUTreeNode treeNode = new EUTreeNode();
            treeNode.setId(itemCat.getId());
            treeNode.setText(itemCat.getName());
            treeNode.setState(itemCat.getIsParent() ? "closed":"open");
            result.add(treeNode);
        }

        return result;
    }
}
