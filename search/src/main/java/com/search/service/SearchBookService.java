package com.search.service;

import com.search.model.SearchBookParam;
import com.search.model.SearchBookResult;

public interface SearchBookService {

	/**
	 * 각 제휴사 도서 검색
	 * @param searchBookParam
	 * @return
	 * @throws Exception
	 */
	SearchBookResult searchBook(SearchBookParam searchBookParam) throws Exception;

}
