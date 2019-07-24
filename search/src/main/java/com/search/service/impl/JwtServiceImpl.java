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
	
	@Override
	public String create(String salt, String dataKey, Map<String, String> data) {
		String jwt = Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setHeaderParam("regDate", System.currentTimeMillis())
				.claim(dataKey, data)
				.signWith(SignatureAlgorithm.HS256, generateKey(salt))
				.compact();
		
		return jwt;
	}

	private byte[] generateKey(String salt) {
		byte[] key = null;
		try {
			key = salt.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			log.error("generate jwt key error: {}", e);
		}
		return key;
	}

	@Override
	public boolean isUsable(String salt, String token) {
		try {
			Jwts.parser().setSigningKey(generateKey(salt)).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			log.info("unauthorized: {}", e.toString());
			throw new UnauthorizedException();
		}
	}
	
	@Override
	public Map<String, String> getData(String salt, String key) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes("UTF-8")).parseClaimsJws(token);
		} catch (Exception e) {
			throw new UnauthorizedException();
		}
		@SuppressWarnings("unchecked")
		Map<String, String> value = (LinkedHashMap<String, String>) claims.getBody().get(key);
		return value;
	}
}
