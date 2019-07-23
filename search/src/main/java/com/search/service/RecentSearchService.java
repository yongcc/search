package com.search.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.search.model.RecentSearch;

public interface RecentSearchService {

	void createRecentSearch(String keyword, HttpServletRequest request, HttpServletResponse response);

	List<RecentSearch> getRecentSearches(HttpServletRequest request);

}
