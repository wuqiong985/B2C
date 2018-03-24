package com.jitb2c.service;

import com.jitb2c.common.pojo.EUDataGridResult;
import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.pojo.TbItem;

/**
 * @ClassName:      ItemService
 * @Description:    商品操作相关service
 * @Author:         wuqiong6
 * @CreateDate:     2018/3/11 16:58
 * @Version:        1.0
 */
public interface ItemService {

    /**
     * 根据Id获取商品
     * @param itemId
     * @return
     */
    TbItem getItemById (long itemId);

    /**
     * 分页查询商品
     * @param page
     * @param rows
     * @return
     */
    EUDataGridResult getItems(int page,int rows);

    /**
     * 添加商品
     * @param item 商品对象
     * @return
     */
    JitB2CResult createItem(TbItem item,String desc,String itemParam) throws Exception;
}
