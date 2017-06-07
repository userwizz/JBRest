package com.mydomain.core;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.HttpClientBuilder;

public class HttpUtils {
	
	private String m_url;
	
	public HttpUtils(String url) {
		m_url = url;
	}
	

	public HttpUriRequest getHttpRequest(String userName){
		
		return new HttpGet( m_url + userName );
	}

	
	public HttpResponse getHttpResponse(HttpUriRequest request) throws ClientProtocolException, IOException{
		
		return HttpClientBuilder.create().build().execute(request);
	}
	
	
}
