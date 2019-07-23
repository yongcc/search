package com.search.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;

public class Utils {
	public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
		Map<Object, Boolean> map = new HashMap<>();
		return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}
	
	public static String removeSpChar(String text) {
		if (StringUtils.isEmpty(text)) {
			return text;
		}
		return text.replaceAll("[^가-힣ㄱ-ㅎㅏ-ㅣa-zA-Z0-9]", "");
	}
}
