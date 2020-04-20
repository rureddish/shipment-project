#@tag
#Feature: Client limited access to data
  #Actor: Client
#
  #@tag1
  #Scenario: search for concluded journeys
    #Given a Client "client" with address "address" email "email" and ref person "refperson"
#		And a second Client "client" with address "address" email "email" and ref person "refperson"
#		And a registered journey from Copenhagen to Hong Kong with "oranges"
    #When client looks at his journeys
    #Then return his journeys
#
  #@tag2
  #Scenario: search for current journeys
    #Given a Client "Andrei" with address "259 Lyngby" email "Andrei@roumania" and ref person "Yann"
    #And a journey in progress
    #And a concluded journey
    #When searching for current journeys
    #Then return the current journey
