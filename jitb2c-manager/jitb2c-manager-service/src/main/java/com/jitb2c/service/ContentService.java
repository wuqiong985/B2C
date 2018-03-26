package com.jitb2c.service;

import com.jitb2c.common.pojo.EUDataGridResult;
import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.pojo.TbContent;

/**
 * Created by wuqiong6 on 2018/3/26.
 */
public interface ContentService {

    EUDataGridResult getContentsById(long categoryId, Integer page, Integer rows);

    JitB2CResult saveContent(TbContent content);

}
