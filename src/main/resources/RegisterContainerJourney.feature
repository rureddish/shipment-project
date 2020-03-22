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
Feature: Create Journey for Container
#Description: A container is registered in a Journey
#Actor: Client

#The system should allow the clients to 
#register containers for journeys. Some 
#information should be entered by the client 
#(e.g., port of origin, destination, content, company), 
#while some information should be 
#automatically created by the system (e.g., journey id).

  @tag1
  Scenario: Succesful Creation of Journey for Container
    Given a client
    And a container with id "DTU01"
    When client creates journey for container with port of origin "Madrid", destination "Copenhagen", content "Oranges", company "DTU"
    Then a journey is created for the container with id "DTU01"
    And new journey id is created automatically
    
  Scenario: Missing Information
    Given a client
    And a container with id "DTU01"
    When client creates journey for container with port of origin "Madrid", destination "Copenhagen", content "Oranges"
    Then error message is displayed

