Narrative:
In order to communicate effectively to the business some functionality
As a development team
I want to use Behaviour-Driven Development


Scenario: Verify that servers needed in tests replies to ping
Meta: 
@info Check how to use tabular data in test

Given Server <server> is needed for tests
When Test host pings server
Then Server should reply to ping

Examples:
|server|
|127.0.0.1|
|google.com|
|github.com|	

					 
Scenario: Check status code for invalid user (SC_NOT_FOUND)
Given Create a new HTTP request using invalid user
When Get HTTP response using invalid user name
Then Response is: 404


Scenario: Check media type (JSON)
Given Create new HTTP request using valid user: userwizz
When Get HTTP response using valid user
Then Media type is: application/json

Scenario: Verify login attribute from JSON payload
Given Create new HTTP request using valid user: userwizz
When Get HTTP response using valid user
Then Verify that value of 'login' is: userwizz