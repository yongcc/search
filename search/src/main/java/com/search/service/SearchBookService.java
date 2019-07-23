package com.search.service;

import com.search.model.SearchBookParam;
import com.search.model.SearchBookResult;

public interface SearchBookService {

	SearchBookResult searchBook(SearchBookParam searchBookParam) throws Exception;

}
