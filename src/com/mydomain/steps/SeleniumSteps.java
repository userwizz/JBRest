package com.mydomain.steps;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.mydomain.pages.PageFactory;

public class SeleniumSteps {
	 
    private final PageFactory pages;
 
    public SeleniumSteps(PageFactory pages) {
        this.pages = pages;
    }
 
    @Given("user opens google search page")
    public void openGoogle() {        
        pages.home().open();        
    }
    
    @When("user searches for $searchText")
    public void performGoogleSeach (String searchText) {
    	pages.home().search(searchText);
    }
    
    @Then("she should see $urlToFind on google result page")
    public void checkResult (String urlToFind) {
    	pages.home().findLinkOnResultPage(urlToFind);
    }

}