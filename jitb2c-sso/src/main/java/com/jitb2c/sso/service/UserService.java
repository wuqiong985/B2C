package com.jitb2c.sso.service;

import com.jitb2c.common.pojo.JitB2CResult;

/**
 * @Author wuqiong6
 * @Date 2018/4/9 13:55
 */
public interface UserService {

    JitB2CResult checkData(String content,Integer type);
}
