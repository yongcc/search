package com.search.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.search.interceptor.AuthInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	private static final String[] AUTH_PATHS = { "/search/**", "/recent/**", "/keyword/**", "/user/id/**" };
	private static final String[] AUTH_EXCLUDE_PATHS = {  };

	@Autowired
	private AuthInterceptor authInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor).addPathPatterns(AUTH_PATHS).excludePathPatterns(AUTH_EXCLUDE_PATHS);
	}
}
