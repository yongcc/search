package com.search;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.search.common.Constants;
import com.search.model.KakaoSearchBookParam;
import com.search.model.KakaoSearchBookResponse;
import com.search.service.ApiService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchApplicationTests {

	@Autowired
	private ApiService apiService;
	
	@Test
	public void contextLoads() throws UnsupportedEncodingException {
		KakaoSearchBookParam param = new KakaoSearchBookParam();
		param.setQuery("카카오");
		
		HttpPost request = new HttpPost(Constants.API_URL.KAKAO_SEARCH_BOOK);
		request.addHeader(HttpHeaders.AUTHORIZATION, Constants.AUTH.KAKAO_API);
//		request.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
		
//		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//		nameValuePairs.add(new BasicNameValuePair("query", param.getQuery()));
//		nameValuePairs.add(new BasicNameValuePair("sort", param.getSort()));
//		nameValuePairs.add(new BasicNameValuePair("page", param.getPage()));
//		nameValuePairs.add(new BasicNameValuePair("size", param.getSize()));
//		nameValuePairs.add(new BasicNameValuePair("target", param.getTarget()));
//		request.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		request.setEntity(new ByteArrayEntity(new Gson().toJson(param).getBytes("UTF-8")));
		
		apiService.sendApi(request, KakaoSearchBookResponse.class);
	}

}
