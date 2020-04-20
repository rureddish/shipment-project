Feature: search journeys
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
    
  #@tag3
  #Scenario: search for journeys by origin
    #Given a Client "Andrei" with address "259 Lyngby" email "Andrei@roumania" and ref person "Yann"
    #And the port of Copenhagen which has 100 containers
    #And a registered journey from Copenhagen to Hong Kong with "oranges"
    #When client searches for journeys coming from "Copenhagen"
    #Then return the journey coming from Copenhagen
#
  #@tag4
  #Scenario: search for journeys by destination
    #Given a Client "Andrei" with address "259 Lyngby" email "Andrei@roumania" and ref person "Yann"
    #And the port of Copenhagen which has 100 containers
    #And a registered journey from Copenhagen to Hong Kong with "oranges"
    #When client searches for journeys bound for "Hong Kong"
    #Then return the journey bounds for Hong Kong

#	@tag5
#  Scenario: search for journeys by client
#    Given a Client "Andrei" with address "259 Lyngby" email "Andrei@roumania" and ref person "Yann"
#    And the port of Copenhagen which has 100 containers
#    And the port of Hong Kong which has 200 containers
#    And a journey from Copenhagen to Hong Kong, registered by the client for a container of "Oranges"
#    When client searchs for his journeys
#    Then return his journey
