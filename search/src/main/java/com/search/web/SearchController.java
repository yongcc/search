package com.search.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search.model.SearchBookParam;
import com.search.model.SearchBookResult;
import com.search.service.KeywordService;
import com.search.service.RecentSearchService;
import com.search.service.SearchService;

@RestController
@RequestMapping("/search")
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private RecentSearchService recentSearchService;
	
	@Autowired
	private KeywordService keywordService;
	
	@RequestMapping("/book")
	public SearchBookResult searchBook(@RequestBody SearchBookParam param, HttpServletRequest request, HttpServletResponse response) {
		if(StringUtils.trimToNull(param.getQuery()) != null) {
			recentSearchService.createRecentSearch(param.getQuery(), request, response);
			keywordService.createKeyword(param.getQuery());
		}
		
		return searchService.searchBook(param);
	}
	
}
