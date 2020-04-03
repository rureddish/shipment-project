Feature: search journeys
  Scenario: search for concluded journeys
    Given a journey list
    And two containers located at the origin
    And a Client "client" with address "address" email "email" and ref person "refperson"
    And a journey in progress
    And a concluded journey
    When searching for concluded journeys
    Then return the concluded journey
