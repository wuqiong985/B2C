package com.jitb2c.portal.service;

import com.jitb2c.portal.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String queryString, int page);
}
