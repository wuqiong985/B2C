package com.jitb2c.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jitb2c.common.pojo.EUDataGridResult;
import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.common.utils.IDUtils;
import com.jitb2c.mapper.TbItemDescMapper;
import com.jitb2c.mapper.TbItemMapper;
import com.jitb2c.pojo.TbItem;
import com.jitb2c.pojo.TbItemDesc;
import com.jitb2c.pojo.TbItemExample;
import com.jitb2c.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private TbItemDescMapper itemDescMapper;

    /**
     * 根据Id查询商品
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

    /**
     * 添加商品
     * @param item 商品对象
     * @return
     */
    @Override
    public JitB2CResult createItem(TbItem item,String desc) throws Exception {
        //item补全
        //1.1生成商品id
        Long itemId = IDUtils.genItemId();
        item.setId(itemId);
        //1.2商品状态，1-正常，2-下架，3-删除
        item.setStatus((byte) 1);
        //1.3创建时间
        item.setCreated(new Date());
        //1.4更新时间
        item.setUpdated(new Date());


        //1.5插入到数据库
        itemMapper.insert(item);

        //2.添加商品描述
        JitB2CResult jitB2CResult = insertItemDesc(itemId,desc);

        if (jitB2CResult.getStatus() != 200){
            throw new Exception();
        }
        return JitB2CResult.ok();
    }

    /**
     * 添加商品描述到数据库
     * @param itemId
     * @param desc
     * @return
     */
    private JitB2CResult insertItemDesc(Long itemId,String desc){
        TbItemDesc itemDesc = new TbItemDesc();
        itemDesc.setItemId(itemId);
        itemDesc.setItemDesc(desc);
        itemDesc.setUpdated(new Date());
        itemDesc.setCreated(new Date());
        itemDescMapper.insert(itemDesc);
        return JitB2CResult.ok();
    }
}
