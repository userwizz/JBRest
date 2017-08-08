package com.mydomain.steps;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Named;
import org.junit.Assert;

import com.mydomain.utils.GitHubUser;
import com.mydomain.utils.HostPinger;
import com.mydomain.utils.HttpUtils;

import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.ContentType;


public class RestSteps {
	
	
	private HostPinger mPinger;
	private String mHost;
	private boolean mIsAlive;
	
	private HttpUtils mHttpUtils;
	private HttpUriRequest mRequest;
	private HttpResponse mHttpResponse;
	
	
	
	public RestSteps (){
		mHttpUtils = new HttpUtils("https://api.github.com/users/");
		mPinger = new HostPinger();
	}
		
	
	// # Ping servers #	
	
	@Given ("Server <server> is needed for tests")
	public void setServer (@Named("server") String server){
		mHost = server;
	}
	
	@When ("Test host pings server")
	public void pingHost () throws IOException, InterruptedException 
	{
		mIsAlive = mPinger.pingHost(mHost);
	}

	@Then ("Server should reply to ping")
	public void verifyPingResponse ()
	{
		Assert.assertTrue("Host \"" + mHost + "\" is not responding to ping! ", mIsAlive);
	}
	
	
	// # Status code #
	
	@Given ("Create a new HTTP request using invalid user")
	public void createHttpRequestUsingRandomUser (){
	
	    mRequest = getHttpRequest(RandomStringUtils.randomAlphabetic( 8 ));
	}
	
	@When ("Get HTTP response using invalid user name")
	public void getHttpResponse () throws ClientProtocolException, IOException
	{
		mHttpResponse = getHttpResponse(mRequest);
		
	}

	@Then ("Response is: $statusCode")
	public void verifyStatusCode (int statusCode)
	{
		Assert.assertEquals(mHttpResponse.getStatusLine().getStatusCode(), statusCode);
	}
	
	
	// # Media type #
	
	@Given ("Create new HTTP request using valid user: $userName")
	public void createHttpRequest(String userName)
	{
		mRequest = getHttpRequest(userName);
	}
	
	@When ("Get HTTP response using valid user")
	public void getHttpResponseValidUser () throws ClientProtocolException, IOException 
	{
		mHttpResponse = getHttpResponse(mRequest);
	}
 
	@Then ("Media type is: $mediaType")
	public void verifyMediaType (String mediaType) {

		Assert.assertEquals( mediaType, ContentType.getOrDefault(mHttpResponse.getEntity()).getMimeType() );

	}
	
	
	// # JSON payload #
		 
	@Then ("Verify that value of 'login' is: $userName")
	public void verifyAttribute(String userName) throws IOException{
			
	    GitHubUser resource = mHttpUtils.parseResourceFromResponse(mHttpResponse, GitHubUser.class);
	    Assert.assertEquals( userName, resource.getLogin());
	}


	private HttpUriRequest getHttpRequest(String userName) {return mHttpUtils.getHttpRequest(userName);}
	private HttpResponse getHttpResponse(HttpUriRequest request) throws ClientProtocolException, IOException {return mHttpUtils.getHttpResponse(request);}
}
