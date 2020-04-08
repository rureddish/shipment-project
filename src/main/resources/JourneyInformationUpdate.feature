@tag
Feature: Journey Information Update

Description : 	The system should allow the logistic company to update journeys
								information (e.g., containers current position).
Actor : Logistic Company
For now client

	@tag1
	  Scenario: Successful update of the container information
	  	Given a client with name "Andrei", address "259 Lyngby", ref person "Yann" and email "Andrei@roumania"
	  	And the port of Copenhagen which has 100 containers
  		And the port of Hong Kong which has 200 containers
	    And a journey from Copenhagen to Hong Kong, registered by the client for a container of "Oranges" 
	    And new container status with temp 12, humidity 70, pressure 1005
	    When the user update the container information
	    Then the container information are updated


