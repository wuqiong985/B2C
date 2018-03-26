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

    /**
     * 删除节点
     * @param id 节点id
     * @return
     */
    @Override
    public JitB2CResult deleteContentCat(long id) {
        deleteContentCatAndChild(id);
        return JitB2CResult.ok();
    }

    /**
     * 更新节点
     * @param id 节点id
     * @param name 需要修改的名称
     * @return
     */
    @Override
    public JitB2CResult updateContentCat(long id, String name) {
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        contentCategory.setName(name);
        contentCategoryMapper.updateByPrimaryKey(contentCategory);
        return JitB2CResult.ok();
    }

    //删除子节点
    public void deleteContentCatAndChild(long id){

        //查询被删除的节点是否是父节点
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        if (!contentCategory.getIsParent()){
            //如果被删除节点的父亲只有它一个子节点，改变父节点的状态
            if (getChildren(contentCategory.getParentId()).size() == 0){
                TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
                parentNode.setIsParent(false);
                contentCategoryMapper.updateByPrimaryKey(parentNode);
            }
            contentCategoryMapper.deleteByPrimaryKey(id);
        }else{
            //如果是父节点，递归删除,先获取所有的子节点
            List<TbContentCategory> children = getChildren(contentCategory.getId());
            //便利删除子节点
            for (TbContentCategory child:children) {
                deleteContentCatAndChild(child.getId());
            }
            //把父节点删了
            contentCategoryMapper.deleteByPrimaryKey(contentCategory.getId());
        }
    }

    //查询该id的父亲所有子节点
    public List<TbContentCategory> getChildren(long parentId){
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> children = contentCategoryMapper.selectByExample(example);
        return children;
    }
}
