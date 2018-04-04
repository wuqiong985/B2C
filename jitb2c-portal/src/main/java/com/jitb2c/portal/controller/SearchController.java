package com.jitb2c.portal.controller;

import com.jitb2c.portal.pojo.SearchResult;
import com.jitb2c.portal.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

/**
 * 商品搜索Controller
 * @Author wuqiong6
 * @Date 2018/3/28 11:32
 */
@Controller
public class SearchController {

    @Autowired
    SearchService searchService;

    /**
     * 通过前台传来的查询条件solr搜索
     * @param queryString 搜索条件
     * @param page  页数
     * @param model 视图，向页面传递参数
     * @return
     */
    @RequestMapping("/search")
    public String search(@RequestParam("q")String queryString, @RequestParam(defaultValue="1")Integer page, Model model) {
        if (queryString != null) {
            try {
                queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        SearchResult searchResult = searchService.search(queryString, page);
        //向页面传递参数
        model.addAttribute("query", queryString);
        model.addAttribute("totalPages", searchResult.getPageCount());
        model.addAttribute("itemList", searchResult.getItemList());
        model.addAttribute("page", page);

        return "search";

    }
}
