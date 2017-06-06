package com.mydomain.steps;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;


public class RestSteps {
	
	private HttpUriRequest request;
	private HttpResponse httpResponse;
	
	@Given ("Create a new HTTP request")
	public void init (){
		
	   String name = RandomStringUtils.randomAlphabetic( 8 );
	    request = new HttpGet( "https://api.github.com/users/" + name );
	}
	
	@When ("Get HTTP response using invalid user name")
	public void getHttpResponse () throws ClientProtocolException, IOException
	{
		httpResponse = HttpClientBuilder.create().build().execute( request );
		
	}

	  
	@Then ("Response is SC_NOT_FOUND")
	public void verifyResponse ()
	{
		System.out.println(httpResponse.getStatusLine());
		Assert.assertEquals(
				httpResponse.getStatusLine().getStatusCode(),
				HttpStatus.SC_NOT_FOUND);
	}

}
