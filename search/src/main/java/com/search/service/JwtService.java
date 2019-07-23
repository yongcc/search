package com.search.service;

import java.util.Map;

public interface JwtService {

	<T> String create(String key, T data, String subject);

	boolean isUsable(String token);

	Map<String, Object> getData(String token);

}
