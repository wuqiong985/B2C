package com.jitb2c.rest.service.impl;

import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.common.utils.ExceptionUtil;
import com.jitb2c.rest.dao.JedisClient;
import com.jitb2c.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Redis数据相关服务
 * @Author wuqiong6
 * @Date 2018/3/27 20:47
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    JedisClient jedisClient;

    @Value("${INDEX_CONTENT_REDIS_KEY}")
    private String INDEX_CONTENT_REDIS_KEY;

    @Override
    public JitB2CResult syncContent(long contentCid) {
        try {
            jedisClient.hdel(INDEX_CONTENT_REDIS_KEY, contentCid + "");
        } catch (Exception e){
            e.printStackTrace();
            return JitB2CResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return JitB2CResult.ok();
    }
}
