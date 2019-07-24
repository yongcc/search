package com.search.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.search.common.Constants;
import com.search.model.Item;
import com.search.model.NaverSearchBookParam;
import com.search.model.NaverSearchBookResponse;
import com.search.model.SearchBook;
import com.search.model.SearchBookParam;
import com.search.model.SearchBookResult;
import com.search.service.ApiService;
import com.search.service.SearchBookService;

@Service(Constants.COMPANY_CODE.NAVER)
public class NaverSearchServiceImpl implements SearchBookService {
	private Logger log = LoggerFactory.getLogger(NaverSearchServiceImpl.class);

	@Autowired
	private ApiService apiService;

	@Override
	public SearchBookResult searchBook(SearchBookParam searchBookParam) throws Exception {
		// rq 세팅
		NaverSearchBookParam param = new NaverSearchBookParam();
		param.setQuery(searchBookParam.getQuery());
		param.setDisplay(searchBookParam.getSize());
		if (searchBookParam.getPage() != null) {
			param.setStart((searchBookParam.getPage() - 1) * (searchBookParam.getSize() != null ? searchBookParam.getSize() : 10) + 1);
		}

		HttpGet request = new HttpGet(Constants.API_URL.NAVER_SEARCH_BOOK + param.getQueryString());
		request.addHeader(Constants.AUTH.NAVER_ID_KEY, Constants.AUTH.NAVER_ID_VALUE);
		request.addHeader(Constants.AUTH.NAVER_SECRET_KEY, Constants.AUTH.NAVER_SECRET_VALUE);

		// 호출
		NaverSearchBookResponse response = apiService.sendApi(request, NaverSearchBookResponse.class);
		if (response == null) {
			log.info("naver api rs is null");
		}

		// 변환
		SearchBookResult result = new SearchBookResult();
		result.setTotalCount(response.getTotal());
		result.setMaxPage(response.getTotal() / (param.getDisplay() == null ? 10 : param.getDisplay()) + 1);

		List<SearchBook> books = new ArrayList<>();
		result.setSearchBooks(books);

		for (Item item : response.getItems()) {
			SearchBook book = new SearchBook();
			book.setTitle(item.getTitle());
			book.setContents(item.getDescription());
			book.setIsbn(item.getIsbn());
			book.setDate(item.getPubdate());
			book.setAuthors(item.getAuthor());
			book.setPublisher(item.getPublisher());
			book.setPrice(item.getPrice());
			book.setSalePrice(item.getDiscount());
			book.setThumbnail(item.getImage());
			books.add(book);
		}

		return result;
	}
}
