package com.search.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.common.Constants;
import com.search.model.SearchBookParam;
import com.search.model.SearchBookResult;
import com.search.service.SearchBookService;
import com.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService {
	private Logger log = LoggerFactory.getLogger(SearchServiceImpl.class);
	
	@Autowired
	private Map<String, SearchBookService> searchBookServices;
	
	@Override
	public SearchBookResult searchBook(SearchBookParam param) {
		SearchBookResult result = null;
		
		// 카카오 서비스 로드
		SearchBookService searchBookService = searchBookServices.get(Constants.COMPANY_CODE.KAKAO);
		try {
			// 카카오 호출
			result = searchBookService.searchBook(param);
			if(result == null) {
				throw new Exception();
			}
		} catch (Exception e) {
			log.warn("kakao search book failed: {}", e);
			
			// 네이버 서비스 로드
			searchBookService = searchBookServices.get(Constants.COMPANY_CODE.NAVER);
			try {
				// 네이버 호출
				result = searchBookService.searchBook(param);
			} catch (Exception e2) {
				log.warn("naver search book failed: {}", e2);
			}
		}
		
		return result;
	}
}
