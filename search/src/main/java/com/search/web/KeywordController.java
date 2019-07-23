package com.search.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.search.model.KeywordInfo;
import com.search.service.KeywordService;

@RestController
public class KeywordController {
	@Autowired
	private KeywordService keywordService;
	
	@RequestMapping("/keyword")
	public List<KeywordInfo> keyword() {
		return keywordService.getKeywordInfos();
	}
}
