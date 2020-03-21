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
Feature: Register Container for Journey
#Description: A container is registered in a Journey
#Actor: Client

#The system should allow the clients to 
#register containers for journeys. Some 
#information should be entered by the client 
#(e.g., port of origin, destination, content, company), 
#while some information should be 
#automatically created by the system (e.g., journey id).

  @tag1
  Scenario: Succesful Registration of Journey by Client
    Given a client
    And 
    When client register journey
    Then a journey exists
    And new id is created

  @tag2
  Scenario Outline: Missing information
    Given I want to write a step with <name>
    When I check for the <value> in step
    Then I verify the <status> in step

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
