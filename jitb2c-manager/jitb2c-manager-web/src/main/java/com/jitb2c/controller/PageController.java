package com.jitb2c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转controller
 * @Author wuqiong6
 * @Date 2018/3/11 14:52
 */
@Controller
public class PageController {
    /**
      * @Description 打开首页
      * @author      wuqiong6
      * @params      []
      * @return      java.lang.String
      * @throws
      */
    @RequestMapping("/")
    public String showIndex(){
        return "index";
    }

    /**
      * @Description 打开页面
      * @author      wuqiong6
      * @params      [page]
      * @return      java.lang.String
      * @throws
      */
    @RequestMapping("/{page}")
    public String showPage(@PathVariable String page){
        return page;
    }
}
