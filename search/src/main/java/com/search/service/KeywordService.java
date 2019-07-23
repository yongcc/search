package com.search.service;

import java.util.List;

import com.search.model.KeywordInfo;

public interface KeywordService {

	List<KeywordInfo> getKeywordInfos();

	void createKeyword(String keyword);

}
