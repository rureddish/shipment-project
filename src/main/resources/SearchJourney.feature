@tag
Feature: search journeys
	@tag1
  Scenario: search for concluded journeys
    Given a client with name "Andrei", address "259 Lyngby", ref person "Yann" and email "Andrei@roumania"
    And the port of Copenhagen which has 100 containers
    And the port of Hong Kong which has 200 containers
    And a journey from Copenhagen to Hong Kong, registered by the client for a container of "Oranges"
    And that the journey is concluded
    When searching for concluded journeys 
    Then return the concluded journey
	
	@tag2
  Scenario: search for current journeys
    Given a client with name "Andrei", address "259 Lyngby", ref person "Yann" and email "Andrei@roumania"
    And the port of Copenhagen which has 100 containers
    And the port of Hong Kong which has 200 containers
    And a journey from Copenhagen to Hong Kong, registered by the client for a container of "Oranges"
    And that the journey is not concluded
    When searching for current journeys 
    Then return the current journey
    
	@tag3
  Scenario: search for journeys by origin
    Given a client with name "Andrei", address "259 Lyngby", ref person "Yann" and email "Andrei@roumania"
    And the port of Copenhagen which has 100 containers
    And the port of Hong Kong which has 200 containers
    And a journey from Copenhagen to Hong Kong, registered by the client for a container of "Oranges"
    When client searchs for journeys coming from "Copenhagen"
    Then return the journey coming from Copenhagen

	@tag4
  Scenario: search for journeys by destination
    Given a client with name "Andrei", address "259 Lyngby", ref person "Yann" and email "Andrei@roumania"
    And the port of Copenhagen which has 100 containers
    And the port of Hong Kong which has 200 containers
    And a journey from Copenhagen to Hong Kong, registered by the client for a container of "Oranges"
    When client searchs for journeys bound for "Hong Kong"
    Then return the journey bounds for Hong Kong
    
#	@tag5
  #Scenario: search for journeys by client
    #Given a client with name "Andrei", address "259 Lyngby", ref person "Yann" and email "Andrei@roumania"
    #And the port of Copenhagen which has 100 containers
    #And the port of Hong Kong which has 200 containers
    #And a journey from Copenhagen to Hong Kong, registered by the client for a container of "Oranges"
    #When client searchs for his journeys 
    #Then return his journey
   