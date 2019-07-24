package com.search.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.search.common.CookieInfo;
import com.search.model.RecentSearch;
import com.search.service.RecentSearchService;
import com.search.utils.CookieUtils;
import com.search.utils.DateUtils;
import com.search.utils.Serializer;
import com.search.utils.Utils;

@Service
public class RecentSearchServiceImpl implements RecentSearchService {
	private Logger log = LoggerFactory.getLogger(RecentSearchServiceImpl.class);
	
	@Override
	public void createRecentSearch(String keyword, HttpServletRequest request, HttpServletResponse response) {
		// 최근검색 조회
		List<RecentSearch> recentSearches = getRecentSearches(request);
		if(recentSearches == null) {
			recentSearches = new ArrayList<>();
		}
		
		// 신규 추가
		RecentSearch search = new RecentSearch();
		search.setKeyword(keyword);
		search.setDate(DateUtils.getCurrentDatetime());
		recentSearches.add(0, search);
		
		// 중복처리, 갯수
		recentSearches = recentSearches.stream()
				.filter(Utils.distinctByKey(RecentSearch::getKeyword))
				.collect(Collectors.toList());
		recentSearches = recentSearches.subList(0, Math.min(CookieInfo.RECENT_SEARCH.MAX_CNT, recentSearches.size()));
		
		// 쿠키 설정
		String serializedValue = Serializer.serializeToBase64(new Gson().toJson(recentSearches));
		CookieUtils.setCookie(response, CookieInfo.RECENT_SEARCH.KEY, serializedValue, CookieInfo.RECENT_SEARCH.EXPIRE);
	}

	@Override
	public List<RecentSearch> getRecentSearches(HttpServletRequest request) {
		String cookieValue = CookieUtils.getCookieValue(request, CookieInfo.RECENT_SEARCH.KEY);
		
		List<RecentSearch> recentSearches = null;
		try {
			String deserializedValue = (String) Serializer.deserializeFromBase64(cookieValue);
			recentSearches = new Gson().fromJson(deserializedValue, new TypeToken<ArrayList<RecentSearch>>(){}.getType());
		} catch (Exception e) {
			log.error("deserialize recentSearch faild: {}", e);
		}
		
		if(recentSearches != null) {
			//recentSearches.sort(Comparator.comparing(RecentSearch::getDate).reversed());
			// 최대 갯수
			recentSearches = recentSearches.subList(0, Math.min(CookieInfo.RECENT_SEARCH.MAX_CNT, recentSearches.size()));
		}
		
		return recentSearches;
	}

}
