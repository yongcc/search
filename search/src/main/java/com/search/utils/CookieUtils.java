package com.search.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class CookieUtils {
	public static List<Cookie> getCookies(HttpServletRequest request, List<String> cookieIds) {
		List<Cookie> result = new ArrayList<Cookie>();
		Cookie[] cookies = request.getCookies();

		if (cookies == null) {
			return result;
		}
		return Arrays.stream(cookies)
				.filter(cookie -> cookieIds.contains(cookie.getName()))
				.filter(cookie -> StringUtils.isNotEmpty(cookie.getValue()))
				.collect(Collectors.toList());
	}

	public static void setCookieWithMaxAge(HttpServletResponse response, String cookieId, String value, int maxAge) {
		Cookie cookie = new Cookie(cookieId, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}
}
