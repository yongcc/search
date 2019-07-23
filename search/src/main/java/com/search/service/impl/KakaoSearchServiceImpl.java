package com.search.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.search.common.Constants;
import com.search.model.Document;
import com.search.model.KakaoSearchBookParam;
import com.search.model.KakaoSearchBookResponse;
import com.search.model.SearchBook;
import com.search.model.SearchBookParam;
import com.search.model.SearchBookResult;
import com.search.service.ApiService;
import com.search.service.SearchBookService;
import com.search.utils.DateUtils;

@Service(Constants.COMPANY_CODE.KAKAO)
public class KakaoSearchServiceImpl implements SearchBookService {
	private Logger log = LoggerFactory.getLogger(KakaoSearchServiceImpl.class);

	@Autowired
	private ApiService apiService;

	@Override
	public SearchBookResult searchBook(SearchBookParam searchBookParam) throws Exception {
		KakaoSearchBookParam param = new KakaoSearchBookParam();
		param.setQuery(searchBookParam.getQuery());
		param.setSize(searchBookParam.getSize());
		param.setPage(searchBookParam.getPage());

		HttpGet request = new HttpGet(Constants.API_URL.KAKAO_SEARCH_BOOK + param.getQueryString());
		request.addHeader(HttpHeaders.AUTHORIZATION, Constants.AUTH.KAKAO_API);

		KakaoSearchBookResponse response = apiService.sendApi(request, KakaoSearchBookResponse.class);
		if (response == null) {
			log.info("kakao api rs is null");
		}

		SearchBookResult result = new SearchBookResult();
		result.setTotalCount(response.getMeta().getTotal_count());

		int maxPage = 1;
		if (response.getMeta().getIs_end()) {
			if (param.getPage() != null) {
				maxPage = param.getPage();
			}
		} else {
			maxPage = response.getMeta().getTotal_count() / response.getDocuments().size() + 1;
		}
		result.setMaxPage(maxPage);

		List<SearchBook> books = new ArrayList<>();
		result.setSearchBooks(books);

		for (Document document : response.getDocuments()) {
			SearchBook book = new SearchBook();
			book.setTitle(document.getTitle());
			book.setContents(document.getContents());
			book.setIsbn(document.getIsbn());
			book.setDate(DateUtils.convertDateString(document.getDatetime()));
			book.setAuthors(String.join(", ", document.getAuthors()));
			book.setPublisher(document.getPublisher());
			book.setPrice(document.getPrice());
			book.setSalePrice(document.getSale_price());
			book.setThumbnail(document.getThumbnail());
			books.add(book);
		}

		return result;
	}

}
