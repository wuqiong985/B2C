package com.jitb2c.rest.service;

import com.jitb2c.common.pojo.JitB2CResult;

/**
 * @Author wuqiong6
 * @Date 2018/3/27 20:45
 */
public interface RedisService{

    /**
     * 当从manager修改前台首页展示广告内容时，需要调用该服务实现redis数据同步
     * @param contentCid
     * @return
     */
    JitB2CResult syncContent(long contentCid);
}
