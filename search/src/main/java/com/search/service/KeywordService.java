package com.search.service;

import java.util.List;

import com.search.model.KeywordInfo;

public interface KeywordService {

	/**
	 * 인기 키워드 조회
	 * @return
	 */
	List<KeywordInfo> getKeywordInfos();

	/**
	 * 키워드 저장
	 * @param keyword
	 */
	void createKeyword(String keyword);

}
