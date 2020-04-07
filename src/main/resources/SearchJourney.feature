Feature: search journeys

  Scenario: search for concluded journeys
    Given a client with name "Andrei", address "259 Lyngby", ref person "Yann" and email "Andrei@roumania"
    And the port of Copenhagen
    And the port of Hong Kong
    And 2 containers located at Copenhagen
    And a journey from Copenhagen to Hong Kong, registered by the client for a container of "Oranges" and with status in progress
    And a journey from Copenhagen to Hong Kong, registered by the client for a container of "Oranges" and with status concluded
    When searching for concluded journeys 
    Then return the concluded journey
