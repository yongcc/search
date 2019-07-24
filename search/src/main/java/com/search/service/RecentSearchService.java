package com.search.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.search.model.RecentSearch;

public interface RecentSearchService {

	/**
	 * 최근 검색 추가
	 * @param keyword
	 * @param request
	 * @param response
	 */
	void createRecentSearch(String keyword, HttpServletRequest request, HttpServletResponse response);

	/**
	 * 최근 검색 조회
	 * @param request
	 * @return
	 */
	List<RecentSearch> getRecentSearches(HttpServletRequest request);

}
