package com.jitb2c.rest.controller;

import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.rest.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Redis缓存同步controller
 * @Author wuqiong6
 * @Date 2018/3/27 20:52
 */
@Controller
@RequestMapping("/cache/sync")
public class RedisController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/content/{contentCid}")
    @ResponseBody
    public JitB2CResult contentCacheSync(@PathVariable Long contentCid){
        JitB2CResult result = redisService.syncContent(contentCid);
        return result;
    }
}
