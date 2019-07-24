package com.search.service.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.common.Constants;
import com.search.dao.KeywordDao;
import com.search.entity.Keyword;
import com.search.model.KeywordInfo;
import com.search.service.KeywordService;
import com.search.utils.Utils;

@Service
public class KeywordServiceImpl implements KeywordService {
	@Autowired
	private KeywordDao keywordDao;

	@Override
	public List<KeywordInfo> getKeywordInfos() {
		// 키워드별 갯수 조회
		List<KeywordInfo> keywordInfos = StreamSupport.stream(keywordDao.findAll().spliterator(), false)
				.collect(Collectors.groupingBy(Keyword::getKeyword, Collectors.counting()))
				.entrySet().stream().map(entry -> {
					KeywordInfo info = new KeywordInfo();
					info.setKeyword(entry.getKey());
					info.setCount(entry.getValue().intValue());
					return info;
				}).sorted((o1, o2) -> o2.getCount() - o1.getCount()).collect(Collectors.toList());
		
		return keywordInfos.subList(0, Math.min(Constants.MAX_KEYWORD_COUNT, keywordInfos.size()));
	}
	
	@Override
	public void createKeyword(String keyword) {
		String convertKeyword = Utils.removeSpChar(keyword);
		if(StringUtils.isEmpty(convertKeyword)) {
			return;
		}
		
		Keyword entity = new Keyword();
		entity.setKeyword(convertKeyword);
		
		keywordDao.save(entity);
	}

}
