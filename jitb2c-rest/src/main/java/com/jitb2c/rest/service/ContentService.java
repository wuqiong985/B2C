package com.jitb2c.rest.service;

import com.jitb2c.pojo.TbContent;

import java.util.List;

/**
 * Created by wuqiong6 on 2018/3/26.
 */
public interface ContentService {

    List<TbContent> getTbContentList(long contentCatId);
}
