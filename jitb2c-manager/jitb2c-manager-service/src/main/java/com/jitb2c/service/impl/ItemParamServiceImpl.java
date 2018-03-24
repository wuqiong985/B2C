package com.jitb2c.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jitb2c.common.pojo.EUDataGridResult;
import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.mapper.TbItemCatMapper;
import com.jitb2c.mapper.TbItemMapper;
import com.jitb2c.mapper.TbItemParamMapper;
import com.jitb2c.pojo.*;
import com.jitb2c.service.ItemCatService;
import com.jitb2c.service.ItemParamService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 商品规格参数模板管理
 * @Author wuqiong6
 * @Date 2018/3/22 23:02
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    TbItemParamMapper itemParamMapper;

    @Autowired
    TbItemCatMapper itemCatMapper;

    @Resource
    ItemCatService itemCatService;
    /**
     * 根据商品类目Id itemCatId获取商品分组信息
     * @param itemCatId
     * @return
     */
    @Override
    public JitB2CResult getItemParamByCid(long itemCatId) {
        TbItemParamExample example = new TbItemParamExample();
        //添加查询条件，若不添加，则默认查询所有
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(itemCatId);

        List<TbItemParam> items = itemParamMapper.selectByExample(example);
        //判断是否查询到结果
        if (items != null && items.size() > 0){
            return JitB2CResult.ok(items.get(0));
        }
        return JitB2CResult.ok();
    }

    /**
     * 分页查询商品规格模板
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EUDataGridResult getItems(Integer page, Integer rows) {
        TbItemParamExample example = new TbItemParamExample();
        //使用分页
        PageHelper.startPage(page,rows);

        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        List<TbItemParamWithCatName> list1 = new ArrayList<>();
        //通过catId查找catName
        for (int i = 0; i < list.size(); i++) {
            TbItemParamWithCatName tbItemParamWithCatName = new TbItemParamWithCatName();
            BeanUtils.copyProperties(list.get(i),tbItemParamWithCatName);
            tbItemParamWithCatName.setItemCatName(itemCatService.getCatName(list.get(i).getItemCatId()));
            list1.add(tbItemParamWithCatName);
        }
        //封装结果
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list1);
        //取总条数
        PageInfo<TbItemParamWithCatName> pageInfo = new PageInfo<>(list1);
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    /**
     * 保存商品规格模板参数
     * @param itemParam
     * @return
     */
    @Override
    public JitB2CResult insertItemParam(TbItemParam itemParam) {
        //补全TbItemParam pojo
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        //插入到规格参数模板表
        itemParamMapper.insert(itemParam);
        return JitB2CResult.ok();
    }
}
