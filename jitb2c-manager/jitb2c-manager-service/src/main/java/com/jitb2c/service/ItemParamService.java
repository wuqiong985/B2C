package com.jitb2c.service;

import com.jitb2c.common.pojo.EUDataGridResult;
import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.pojo.TbItemParam;


/**
 * @Author wuqiong6
 * @Date 2018/3/22 22:58
 */
public interface ItemParamService {

    JitB2CResult getItemParamByCid(long itemCatId);

    EUDataGridResult getItems(Integer page, Integer rows);

    JitB2CResult insertItemParam(TbItemParam itemParam);
}
