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
Feature: Advanced Queries
	Simple search: Logistic - Find Clients by name, email. Find Journey by port or city. 
	Advanced search: ClientwithMost Journeys

  @tag1
  Scenario: Client with most Journeys
    Given an admin with password "admin"
    And client list containing the client
    When admin searches for clients with most journeys
    Then the client appears in search results
    
