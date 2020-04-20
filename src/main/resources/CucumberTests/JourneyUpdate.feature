@tag
Feature: Journey Information Update

Description : 	The system should allow the logistic company to update journeys
								information (e.g., containers current position).
Actor : Logistic Company


	@tag1
	  Scenario: Updating cargo embarkation
		Given a Client "Andrei" with address "259 Lyngby" email "Andrei@roumania" and ref person "Yann"
	  And a worker of the logistic company
	  And the port of Copenhagen which has 100 containers
  	And the port of Hong Kong which has 200 containers
		And a registered journey from Copenhagen to Hong Kong with "oranges"
		And a ship with ID 23 in Copenhagen
	  When the worker informs of the embarkation of the ship transporting the container
	  Then the ship and the container are at sea
