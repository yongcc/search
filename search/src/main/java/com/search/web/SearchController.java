package com.search.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search.model.SearchBookParam;
import com.search.model.SearchBookResult;
import com.search.service.SearchService;

@RestController
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@RequestMapping("/searchBook")
	public SearchBookResult searchBook(SearchBookParam param) {
		return searchService.searchBook(param);
	}
}
