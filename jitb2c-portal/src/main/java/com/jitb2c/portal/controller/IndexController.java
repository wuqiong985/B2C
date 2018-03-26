package com.jitb2c.portal.controller;

import com.jitb2c.common.pojo.JitB2CResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 展示首页controller
 * @Author wuqiong6
 * @Date 2018/3/25 10:37
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String showIndex(){
        return "index";
    }

    /**
     * 测试HttpClient模拟post请求
     */
    @RequestMapping(value = "/httpclient/post",method = RequestMethod.POST)
    @ResponseBody
    public String testPost(){
        return "ok";
    }

    /**
     * 测试HttpClient模拟post带参数请求
     */
    @RequestMapping(value = "/httpclient/post1",method = RequestMethod.POST)
    @ResponseBody
    public String testPost(String username,String password){
        return "username:"+username+" password: "+password;
    }
}
