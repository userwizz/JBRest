package com.mydomain.steps;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;

import com.mydomain.core.HttpUtils;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.ContentType;


public class RestSteps {
	
	
	private HttpUtils httpUtils;
	private HttpUriRequest request;
	private HttpResponse httpResponse;
	
	
	public RestSteps (){
		httpUtils = new HttpUtils("https://api.github.com/users/");
	}
		
	
	// # Status code #
	
	@Given ("Create a new HTTP request using invalid user")
	public void createHttpRequestUsingRandomUser (){
	
	    request = httpUtils.getHttpRequest(RandomStringUtils.randomAlphabetic( 8 ));
	}
	
	@When ("Get HTTP response using invalid user name")
	public void getHttpResponse () throws ClientProtocolException, IOException
	{
		httpResponse = httpUtils.getHttpResponse(request);
		
	}

	@Then ("Response is SC_NOT_FOUND")
	public void verifyStatusCode ()
	{
		Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),HttpStatus.SC_NOT_FOUND);
	}
	
	
	// # Media type #
	
	@Given ("Create new HTTP request using valid user: $userName")
	public void createHttpRequest(String userName)
	{
		request = httpUtils.getHttpRequest(userName);
	}
	
	@When ("Get HTTP response using valid user")
	public void getHttpResponseValidUser () throws ClientProtocolException, IOException
	{
		httpResponse = httpUtils.getHttpResponse(request);
	}
 
	@Then ("Media type is $mediaType")
	public void verifyMediaType (String mediaType) {

		Assert.assertEquals( mediaType, ContentType.getOrDefault(httpResponse.getEntity()).getMimeType() );

	}
	
	
	// # JSON payload #
	// ToDo

}
