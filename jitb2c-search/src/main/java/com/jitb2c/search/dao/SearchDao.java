package com.jitb2c.search.dao;

import com.jitb2c.search.pojo.SearchResult;
import org.apache.solr.client.solrj.SolrQuery;

/**
 * @Author wuqiong6
 * @Date 2018/3/28 8:45
 */
public interface SearchDao {

    SearchResult search(SolrQuery query) throws Exception;
}
