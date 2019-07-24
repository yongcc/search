package com.search.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.search.common.Constants;
import com.search.exception.UnauthorizedException;
import com.search.service.JwtService;

@Service
public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	private JwtService jwtService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		final String token = request.getHeader(HttpHeaders.AUTHORIZATION);

		try {
			jwtService.isUsable(Constants.JWT.LOGIN_SALT, token);
			return true;
		} catch (UnauthorizedException e) {
			response.sendError(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
		}

		return false;
	}
}
