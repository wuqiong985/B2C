package com.jitb2c.service;

import com.jitb2c.common.pojo.EUTreeNode;
import com.jitb2c.common.pojo.JitB2CResult;

import java.util.List;

/**
 * @Author wuqiong6
 * @Date 2018/3/25 21:04
 */
public interface ContentCatService {

    List<EUTreeNode> getContentCatList(long parentId);

    JitB2CResult insertContentCat(long parentId,String name);

    JitB2CResult deleteContentCat(long parentId,long id);
}
