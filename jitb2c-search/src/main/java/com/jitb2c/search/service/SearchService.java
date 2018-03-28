package com.jitb2c.search.service;

import com.jitb2c.search.pojo.SearchResult;

/**
 * @Author wuqiong6
 * @Date 2018/3/28 9:34
 */
public interface SearchService {

    SearchResult search(String queryString, int page, int rows) throws Exception;
}
