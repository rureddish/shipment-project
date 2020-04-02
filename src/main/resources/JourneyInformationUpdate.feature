#Author: your.email@your.domain.com
#Keywords Summary :
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)
#Sample Feature Definition Template
@tag
Feature: Journey Information Update

Description : 	The system should allow the logistic company to update journeys
								information (e.g., containers current position).
Actor : Logistic Company  
#For now client

	@tag1
	  Scenario: Successful update of the journey information
	  	Given a user
	    And a container of "Oranges", with ID "containerID", owner ID "ownerID" and registered for a journey from "Copenhagen" to "Hong Kong" with company "CGM"
	    And a database containing the journey
	    And new information on the journey
	    When the user update the journey information
	    Then the journey information are updated
	 

