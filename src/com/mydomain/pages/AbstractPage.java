package com.mydomain.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;

public abstract class AbstractPage extends WebDriverPage {

	
    public AbstractPage(WebDriverProvider driverProvider) {
    	super(driverProvider);
    }
    
    // Add common stuff for pages here

    public void setWaitTimeout (int timeout) {
    	manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

}
