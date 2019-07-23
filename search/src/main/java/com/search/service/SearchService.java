package com.search.service;

import com.search.model.SearchBookParam;
import com.search.model.SearchBookResult;

public interface SearchService {

	SearchBookResult searchBook(SearchBookParam param);

}
