package com.search.service;

import com.search.model.SearchBookParam;
import com.search.model.SearchBookResult;

public interface SearchService {

	/**
	 * 도서 검색
	 * @param param
	 * @return
	 */
	SearchBookResult searchBook(SearchBookParam param);

}
