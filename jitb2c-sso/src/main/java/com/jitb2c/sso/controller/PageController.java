package com.jitb2c.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转controller
 * @Author wuqiong6
 * @Date 2018/4/9 22:04
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/register")
    public String showRegister(){
        return "register";
    }

    @RequestMapping("/login")
    public String showLogin(){
        return "login";
    }
}
