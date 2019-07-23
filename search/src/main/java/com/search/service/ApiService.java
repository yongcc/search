package com.search.service;

import org.apache.http.client.methods.HttpUriRequest;

public interface ApiService {

	<T> T sendApi(HttpUriRequest request, Class<T> responseClass);

}
