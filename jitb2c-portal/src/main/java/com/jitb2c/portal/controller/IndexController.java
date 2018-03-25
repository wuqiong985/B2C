package com.jitb2c.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
