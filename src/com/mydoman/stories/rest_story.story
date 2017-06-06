Sample story

Narrative:
In order to communicate effectively to the business some functionality
As a development team
I want to use Behaviour-Driven Development
					 
Scenario:  Check that we get SC_NOT_FOUND for invalid user
Given Create a new HTTP request
When Get HTTP response using invalid user name
Then Response is SC_NOT_FOUND