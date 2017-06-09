package com.mydomain.utils;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;



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
	
	
	public <T>T parseResourceFromResponse(HttpResponse response, Class<T> c) throws IOException {
			  
			    String jsonFromResponse = EntityUtils.toString(response.getEntity());
			    ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			    
			    return mapper.readValue(jsonFromResponse, c);
			}
	
}
