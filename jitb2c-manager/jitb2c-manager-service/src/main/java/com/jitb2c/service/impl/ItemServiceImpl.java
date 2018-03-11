package com.jitb2c.service.impl;

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
}
