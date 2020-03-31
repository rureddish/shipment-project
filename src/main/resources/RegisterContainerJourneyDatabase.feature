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
Feature: Create Journey for Container - Database

  @tag1
  Scenario: Successful registration of containers for journey
  	Given a client with name "Andrei", address "259 Lyngby", ref person "Yann" and email "Andrei@roumania" 
    And an empty container
    And a database not containing the journey and the container
    When client registers a container of "Oranges" for a journey from "Copenhagen" to "Hong Kong" with the company "CGM"
    Then the journey that contains the container is stored in the database
    And a message SuccessfulRegistration is displayed
    
  #Scenario: Missing Information / Wrong information
    #Given a client with name "Andrei", address "259 Lyngby", ref person "Yann" and email "Andrei@roumania" 
    #And an empty container
    #When client registers ...
    #Then error message MissingInformation / WrongInformation is displayed

