package com.jitb2c.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jitb2c.common.pojo.EUDataGridResult;
import com.jitb2c.mapper.TbItemMapper;
import com.jitb2c.pojo.TbItem;
import com.jitb2c.pojo.TbItemExample;
import com.jitb2c.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName:      ItemServiceImpl
 * @Description:    商品管理service
 * @Author:         wuqiong6
 * @CreateDate:     2018/3/10 19:43
 * @Version:        1.0
 */
@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private TbItemMapper itemMapper;

    /**
     *
     * @param itemId
     * @return
     */
    @Override
    public TbItem getItemById(long itemId) {

//        TbItem item = itemMapper.selectByPrimaryKey(itemId);

        TbItemExample example = new TbItemExample();

        //添加查询条件，若不添加，则默认查询所有
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(itemId);

        List<TbItem> items = itemMapper.selectByExample(example);

        if (items != null && items.size() > 0){
            return items.get(0);
        }
        return null;
    }

    /**
     * 商品列表查询
     * @param page
     * @param rows
     * @return
     */
    @Override
    public EUDataGridResult getItems(int page,int rows) {
        TbItemExample example = new TbItemExample();

        //使用分页
        PageHelper.startPage(page,rows);

        List<TbItem> list = itemMapper.selectByExample(example);

        //封装结果
        EUDataGridResult result = new EUDataGridResult();
        result.setRows(list);

        //取总条数
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);

        result.setTotal(pageInfo.getTotal());

        return result;
    }
}
