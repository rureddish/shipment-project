@tag
Feature: Journey Information Update

Description : 	The system should allow the logistic company to update journeys
								information (e.g., containers current position).
Actor : Logistic Company
#For now client

	@tag1
	Scenario: Ship departure with container
		Given a Client "Andrei" with address "259 Lyngby" email "Andrei@roumania" and ref person "Yann"
		And the port of Copenhagen which has 1 containers
		And the port of Hong Kong which has 1 containers
		And a registered ship in Copenhagen
		And a registered journey from Copenhagen to Hong Kong with "oranges"
		And the container is added to the ship
		When the worker informs of the departure of the ship transporting the container
		Then the ship and the container are at sea