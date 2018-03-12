package com.jitb2c.service;

import com.jitb2c.common.pojo.EUTreeNode;

import java.util.List;

/**
 * 目录操作的类
 * Created by wuqiong6 on 2018/3/12.
 */
public interface ItemCatService {

    List<EUTreeNode> getCatList(long parentId);
}
