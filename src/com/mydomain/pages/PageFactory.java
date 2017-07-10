package com.mydomain.pages;

import org.jbehave.web.selenium.WebDriverProvider;

public class PageFactory {
	 
	
    private final WebDriverProvider m_provider;
    private GoogleHome m_home = null;

 
    public PageFactory(WebDriverProvider driverProvider) {
        this.m_provider = driverProvider;
    }

    
    // Add more pages as needed
    public GoogleHome home(){ 
    	if (m_home == null ) {return new GoogleHome(m_provider);}
    	else return m_home;
    }
     
}