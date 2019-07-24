package com.search.service;

import java.util.Map;

public interface JwtService {

	/**
	 * 토큰 생성
	 * @param salt
	 * @param dataKey
	 * @param data
	 * @return
	 */
	String create(String salt, String dataKey, Map<String, String> data);

	/**
	 * 토큰 사용가능여부
	 * @param salt
	 * @param token
	 * @return
	 */
	boolean isUsable(String salt, String token);

	/**
	 * 저장 데이터 조회
	 * @param salt
	 * @param key
	 * @return
	 */
	Map<String, String> getData(String salt, String key);


}
