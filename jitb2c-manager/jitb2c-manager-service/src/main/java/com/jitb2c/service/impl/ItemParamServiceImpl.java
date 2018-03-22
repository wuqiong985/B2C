package com.jitb2c.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jitb2c.common.pojo.EUDataGridResult;
import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.mapper.TbItemCatMapper;
import com.jitb2c.mapper.TbItemMapper;
import com.jitb2c.mapper.TbItemParamMapper;
import com.jitb2c.pojo.*;
import com.jitb2c.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author wuqiong6
 * @Date 2018/3/22 23:02
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    TbItemParamMapper itemParamMapper;

    @Autowired
    TbItemCatMapper itemCatMapper;

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
        criteria.andIdEqualTo(itemCatId);

        List<TbItemParam> items = itemParamMapper.selectByExampleWithBLOBs(example);

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

//        for (TbItemParam itemParam:list){
//            TbItemCatExample example1 = new TbItemCatExample();
//            TbItemCatExample.Criteria criteria = example1.createCriteria();
//            criteria.andParentIdEqualTo(itemParam.getItemCatId());
//            List<TbItemCat> itemCat = itemCatMapper.selectByExample(example1);
//            itemParam.setItemCat(itemCat.get(0).getName());
//        }

        //封装结果
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);

        //取总条数
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);

        result.setTotal(pageInfo.getTotal());

        return result;
    }
}
