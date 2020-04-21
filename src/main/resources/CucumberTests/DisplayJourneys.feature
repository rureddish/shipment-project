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
