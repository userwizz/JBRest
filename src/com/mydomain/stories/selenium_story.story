Simple tests using selenium/jbehave-web

Narrative:
In order to communicate effectively to the business some functionality
As a development team
I want to use Behaviour-Driven Development
					 
Scenario:  Verify that jbehave.org is present on google results
Given user opens google search page
When user searches for jbehave
Then she should see jbehave.org on google result page
