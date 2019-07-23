package com.search.service.impl;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.dao.KeywordDao;
import com.search.entity.Keyword;
import com.search.entity.User;
import com.search.service.KeywordService;

@Service
public class KeywordServiceImpl implements KeywordService {
	@Autowired
	private KeywordDao keywordDao;

//	@Override
//	public Iterable<Keyword> getKeywords() {
//		StreamSupport.stream(keywordDao.findAll().spliterator(), false).collect(groupingBy(Keyword::getKeyword));
//		
//		
//	}

}
