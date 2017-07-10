package com.mydomain.pages;

import java.util.List;

import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Assert;



public class GoogleHome extends AbstractPage {

	
	// class for handling google search page
	
    public GoogleHome(WebDriverProvider webDriverProvider) {
        super(webDriverProvider);
    }

    public void open() {

    	setWaitTimeout(10);
    	get("http://www.google.com");
    	
    }
    
    public void search(String stringToSearch) {
    	
    	WebElement searchBox = findElement(By.name("q"));
    	searchBox.sendKeys(stringToSearch);
    	searchBox.submit();

    }

	public void findLinkOnResultPage(String urlToFind) {

		boolean isFound = false;
	    List<WebElement> findElements = findElements(By.xpath("//*[@id='rso']//h3/a"));
	    
	    for (WebElement webElement : findElements)
	    {
	    	if (webElement.getAttribute("href").contains(urlToFind)) {
	        	isFound = true;
	        	break;}
	    }
	    
	    if (!isFound) Assert.fail(urlToFind + " not found on result page!");
	}

}
