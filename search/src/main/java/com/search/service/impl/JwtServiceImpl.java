package com.search.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.search.exception.UnauthorizedException;
import com.search.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {
	private Logger log = LoggerFactory.getLogger(JwtServiceImpl.class);
	
	private static final String SALT = "luvookSecret";

	@Override
	public <T> String create(String key, T data, String subject) {
		String jwt = Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setHeaderParam("regDate", System.currentTimeMillis())
				.setSubject(subject).claim(key, data)
				.signWith(SignatureAlgorithm.HS256, generateKey())
				.compact();
		
		return jwt;
	}

	private byte[] generateKey() {
		byte[] key = null;
		try {
			key = SALT.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("generate jwt key error: {}", e);
		}
		return key;
	}

	@Override
	public boolean isUsable(String token) {
		try {
			Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			log.info("unauthorized: {}", e);
			throw new UnauthorizedException();
		}
	}
	
	@Override
	public Map<String, Object> getData(String token) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String jwt = request.getHeader(HttpHeaders.AUTHORIZATION);
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(jwt);
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> value = (LinkedHashMap<String, Object>) claims.getBody().get(token);
		return value;
	}
}
