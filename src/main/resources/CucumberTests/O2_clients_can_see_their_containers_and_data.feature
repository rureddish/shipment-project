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
Feature: Clients can see their containers and data but not other clients'
  Actor: Client

  @tag1
  Scenario: search for concluded journeys
    Given a Client "client" with address "address" email "email" and ref person "refperson"
    And a journey in progress
    And a concluded journey
    When searching for concluded journeys
    Then return the concluded journey

  @tag2
  Scenario: search for current journeys
    Given a Client "Andrei" with address "259 Lyngby" email "Andrei@roumania" and ref person "Yann"
    And a journey in progress
    And a concluded journey
    When searching for current journeys
    Then return the current journey
