package com.jitb2c.rest.service;

import com.jitb2c.common.pojo.JitB2CResult;

/**
 * @Author wuqiong6
 * @Date 2018/4/6 21:23
 */
public interface ItemService {

    JitB2CResult getItemBaseInfo(long itemId);

    JitB2CResult getItemDesc(long itemId);

    JitB2CResult getItemParam(long itemId);
}
