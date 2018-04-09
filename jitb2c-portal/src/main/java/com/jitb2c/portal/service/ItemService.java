package com.jitb2c.portal.service;

import com.jitb2c.portal.pojo.ItemInfo;

/**
 * 前台商品相关信息查询展示
 * @Author wuqiong6
 * @Date 2018/4/8 16:18
 */
public interface ItemService {

    ItemInfo getItemById(long itemId);

    String getItemDescById(long itemId);

    String getItemParam(long itemId);
}
