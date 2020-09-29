# FetchRewards

### Language - Java | Backend Framework - Spring Boot | Testing Framework - Postman

This is a web service which accept a list of email addresses and return an integer indicating the number of unique email addresses.

Unique email addresses means they will be delivered to the same account using Gmail account matching. Specifically: Gmail will ignore the placement of "." in the username. And it will ignore any portion of the username after a "+".

Examples
test.email@gmail.com, test.email+spam@gmail.com and testemail@gmail.com will all go to the same address, and thus the result should be 1.
test.email@gmail.com and test.email@fetchrewards.com are two different email addresses, and thus the result should be 2.

### How to run:
1) Run the main class - FetchRewardsApplication. It can be run as Java Application because Spring Boot has embedded Tomcat or run as Spring Boot application.
2) Once the server has started, the application is ready to serve incoming web requests.
3) Go to the postman - Choose GET method and type "http://localhost:8080/emails/list/"
4) Under Body select raw and then give the input as {"emails":["test.email@gmail.com", "test.email@fetchrewards.com"]}. Feel free to insert your own sample emails. 
5) Hit Send Button (For this input, you should see Status Code 200 and output as '2' because both emails are unique).
