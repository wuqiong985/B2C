package com.jitb2c.portal.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jitb2c.common.pojo.JitB2CResult;
import com.jitb2c.common.utils.HttpClientUtil;
import com.jitb2c.portal.pojo.SearchResult;
import com.jitb2c.portal.service.SearchService;

/**
 * 商品搜索Service
 * @Author wuqiong6
 * @Date 2018/3/28 8:43
 */
@Service
public class SearchServiceImpl implements SearchService {

	@Value("${SEARCH_BASE_URL}")
	private String SEARCH_BASE_URL;
	
	@Override
	public SearchResult search(String queryString, int page) {
		// 调用jitb2c-search的服务
		//查询参数
		Map<String, String> param = new HashMap<>();
		param.put("q", queryString);
		param.put("page", page + "");
		try {
			//调用服务	http://localhost:8084/search/query
			String json = HttpClientUtil.doGet(SEARCH_BASE_URL, param);
			//把字符串转换成java对象
			JitB2CResult jitB2CResult = JitB2CResult.formatToPojo(json, SearchResult.class);
			if (jitB2CResult.getStatus() == 200) {
				SearchResult result = (SearchResult) jitB2CResult.getData();
				return result;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
