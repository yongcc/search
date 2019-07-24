package com.search.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search.model.RecentSearch;
import com.search.service.RecentSearchService;

@RestController
@RequestMapping("/recent")
public class RecentSearchController {
	
	@Autowired
	private RecentSearchService recentSearchService;
	
	/**
	 * 도서 최근 검색 조회
	 * @param request
	 * @return
	 */
	@RequestMapping("/book")
	public List<RecentSearch> recentSearch(HttpServletRequest request) {
		return recentSearchService.getRecentSearches(request);
	}
}
