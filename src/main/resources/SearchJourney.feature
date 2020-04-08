Feature: search journeys

  Scenario: search for concluded journeys
    Given a client with name "Andrei", address "259 Lyngby", ref person "Yann" and email "Andrei@roumania"
    And the port of Copenhagen which has 100 containers
    And the port of Hong Kong which has 200 containers
    And a journey from Copenhagen to Hong Kong, registered by the client for a container of "Oranges"
    And that the journey is concluded
    When searching for concluded journeys 
    Then return the concluded journey
