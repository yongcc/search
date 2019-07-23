package com.search.service.impl;

import javax.annotation.PostConstruct;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.search.service.ApiService;

@Service
public class ApiServiceImpl implements ApiService {
	private Logger log = LoggerFactory.getLogger(ApiService.class);

	private static final int DEFAULT_TIMEOUT = 100000;

	private static PoolingHttpClientConnectionManager defaultManager = null;
	private static CloseableHttpClient defaultHttpClient = null;
	
	@PostConstruct
	public void init() {
		defaultManager = new PoolingHttpClientConnectionManager();
		defaultManager.setMaxTotal(100);
		defaultManager.setDefaultMaxPerRoute(50);
		
		RequestConfig config = RequestConfig.custom()
				.setConnectTimeout(DEFAULT_TIMEOUT)
				.setConnectionRequestTimeout(DEFAULT_TIMEOUT)
				.setSocketTimeout(DEFAULT_TIMEOUT)
				.build();
		
		defaultHttpClient = HttpClients.custom()
		.setConnectionManager(defaultManager)
		.setDefaultRequestConfig(config)
		.build();
	}

	@Override
	public <T> T sendApi(HttpUriRequest request, Class<T> responseClass) {
		long startTime = System.currentTimeMillis();

		T apiRs = null;
		try {
			String json = EntityUtils.toString(defaultHttpClient.execute(request).getEntity());
			log.info("sendApi Rs: {}", json);
			apiRs = new Gson().fromJson(json, responseClass);
			
			log.info("sendApi Rs: {}", json);
		} catch (Exception e) {
			log.error("sendApi error : {}", e);
		} finally {
			log.info("sendApi time : {}", System.currentTimeMillis() - startTime);
		}

		return apiRs;
	}
}
