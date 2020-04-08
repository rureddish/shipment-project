@tag
Feature: Register Container for Journey

Description: 	The system should allow the clients to register containers for journeys.
							Some information should be entered by the client (e.g., port of origin,
							destination, content, company), while some information should be
							automatically created by the system (e.g., journey id).
Actor: 	Client

  @tag1
  Scenario: Successful registration of containers for journey
  	Given a client with name "Andrei", address "259 Lyngby", ref person "Yann" and email "Andrei@roumania"
  	And the port of Copenhagen which has 100 containers
  	And the port of Hong Kong which has 200 containers
    When client registers a container of "Oranges" for a journey from Copenhagen to Hong Kong
    Then the container is registered for the journey

