package com.search.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search.model.KeywordInfo;
import com.search.service.KeywordService;

@RestController
@RequestMapping(("/keyword"))
public class KeywordController {
	@Autowired
	private KeywordService keywordService;
	
	/**
	 * 책 인기 키워드 조회
	 */
	@RequestMapping("/book")
	public List<KeywordInfo> keyword() {
		return keywordService.getKeywordInfos();
	}
}
