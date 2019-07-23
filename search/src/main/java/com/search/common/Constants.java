package com.search.common;

import java.util.Arrays;
import java.util.List;

public class Constants {
	public static class AUTH {
		public static final String KAKAO_API = "KakaoAK 68aafdd89b7513fb0855470b43245cb8";
		public static final String NAVER_ID_KEY = "X-Naver-Client-Id";
		public static final String NAVER_ID_VALUE = "nwqfGYFYripppyEcaRUL";
		public static final String NAVER_SECRET_KEY = "X-Naver-Client-Secret";
		public static final String NAVER_SECRET_VALUE = "83QBk8WEnA";
	}
	
	public static class API_URL {
		public static final String KAKAO_SEARCH_BOOK = "https://dapi.kakao.com/v3/search/book";
		public static final String NAVER_SEARCH_BOOK = "https://openapi.naver.com/v1/search/book.json";
	}
	
	public static class COMPANY_CODE {
		public static final String KAKAO = "K";
		public static final String NAVER = "N";
	}
	
	public static final List<String> RECENT_SEARCH_KEYS = Arrays.asList("rsKey1", "rsKey2", "rsKey3");
	public static final Integer RECENT_SEARCH_MAX_CNT = 10;
	public static final Integer RECENT_SEARCH_EXPIRE = 30;
}
