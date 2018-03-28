package com.jitb2c.search.controller;

import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.common.utils.ExceptionUtil;
import com.jitb2c.search.pojo.SearchResult;
import com.jitb2c.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 搜索Controller
 * @Author wuqiong6
 * @Date 2018/3/28 9:50
 */
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * 根据参数查询商品信息
     * @param queryString 搜索条件
     * @param page 页数
     * @param rows 记录总条数
     * @return
     */
    @RequestMapping(value="/query", method= RequestMethod.GET)
    @ResponseBody
    public JitB2CResult search(@RequestParam("q") String queryString,
                               @RequestParam(defaultValue="1") Integer page,
                               @RequestParam(defaultValue="60") Integer rows) {
        //查询条件不能为空
        if (StringUtils.isBlank(queryString)) {
            return JitB2CResult.build(400, "查询条件不能为空");
        }
        SearchResult searchResult = null;
        try {
            queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
            searchResult = searchService.search(queryString, page, rows);
        } catch (Exception e) {
            e.printStackTrace();
            return JitB2CResult.build(500, ExceptionUtil.getStackTrace(e));
        }
        return JitB2CResult.ok(searchResult);

    }
}
