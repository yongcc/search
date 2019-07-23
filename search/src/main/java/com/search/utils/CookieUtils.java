package com.search.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	public static void setCookie(HttpServletResponse response, String cookieId, String value, int maxAge) {
		Cookie cookie = new Cookie(cookieId, value);
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	public static String getCookieValue(HttpServletRequest request, String cookieId) {
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return null;
		}
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(cookieId)) {
				return cookie.getValue();
			}
		}
		return null;
		//return Arrays.stream(cookies).filter(cookie -> cookieIds.contains(cookie.getName())).filter(cookie -> StringUtils.isNotEmpty(cookie.getValue())).collect(Collectors.toList());
	}
	
}
