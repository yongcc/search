package com.search.service;

import java.util.Map;

public interface JwtService {

	String create(String salt, String dataKey, Map<String, String> data);

	boolean isUsable(String salt, String token);

	Map<String, String> getData(String salt, String key);


}
