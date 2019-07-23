package com.search.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.search.common.Constants;
import com.search.model.RecentSearch;
import com.search.service.RecentSearchService;
import com.search.utils.CookieUtils;
import com.search.utils.Serializer;

@Service
public class RecentSearchServiceImpl implements RecentSearchService {

	public void createRecentSearch(RecentSearch recentSearch, HttpServletRequest request, HttpServletResponse response) {

		List<RecentSearch> recentSearchList = null;
		List<RecentSearch> filterList = new ArrayList<>();

		try {
			recentSearchList = this.getRecentSearchList(request);
		} catch (Exception e) {
			return;
		}

		// 지난 일정, 중복 여정 쿠키 삭제
		for (RecentSearch rs : recentSearchList) {
			if (StringUtils.equals(rs.getKeyword(), recentSearch.getKeyword())) {
				CookieUtils.setCookieWithMaxAge(response, rs.getCookieId(), null, 0);
				continue;
			}
			filterList.add(rs);
		}

		// 사용 가능한 Cookie Keys
		List<String> enableKeys = new ArrayList<>();
		enableKeys.addAll(Constants.RECENT_SEARCH_KEYS);
		for (RecentSearch rs : filterList) {
			enableKeys.remove(rs.getCookieId());
		}

		// 가장 나중에 등록된 쿠키 삭제
		int listSize = filterList.size();
		if (enableKeys.size() == 0) {
			RecentSearch removeData = filterList.get(listSize - 1);
			CookieUtils.setCookieWithMaxAge(response, removeData.getCookieId(), null, 0);
			enableKeys.add(removeData.getCookieId());
		}

		// 쿠키 추가
		if (enableKeys.size() != 0) {
			recentSearch.setCookieId(enableKeys.get(0));
			String encodeValue = Serializer.serializeToBase64(new Gson().toJson(recentSearch));
			CookieUtils.setCookieWithMaxAge(response, enableKeys.get(0), encodeValue, Constants.RECENT_SEARCH_EXPIRE);
		}
	}

	public List<RecentSearch> getRecentSearchList(HttpServletRequest request) {
		List<RecentSearch> retList = new ArrayList<>();
		List<Cookie> cookieList = CookieUtils.getCookies(request, Constants.RECENT_SEARCH_KEYS);

		if (cookieList == null || cookieList.isEmpty()) {
			return retList;
		}

		for (Cookie cookie : cookieList) {
			try {
				String value = (String) Serializer.deserializeFromBase64(cookie.getValue());
//				recentSearch.setCookieId(cookie.getName());
				retList.add(new Gson().fromJson(value, RecentSearch.class));
			} catch (Exception e) {
			}
		}

		retList.sort(Comparator.comparing(RecentSearch::getDate).reversed());
		return retList.subList(0, Math.min(Constants.RECENT_SEARCH_MAX_CNT, retList.size() - 1));
	}

}
