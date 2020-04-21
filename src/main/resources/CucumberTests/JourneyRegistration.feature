@tag
Feature: Register Container for Journey

Description: 	The system should allow the clients to register containers for journeys.
							Some information should be entered by the client (e.g., port of origin,
							destination, content, company), while some information should be
							automatically created by the system (e.g., journey id).
Actor: 	Client

Journey(Port origin, Port destination, String cargo, Client client)
	@tag1
	Scenario: Successful registration of containers for journey
		Given a Client "Andrei" with address "259 Lyngby" email "Andrei@roumania" and ref person "Yann"
		And the port of Copenhagen which has 1 containers
		And the port of Hong Kong which has 1 containers
		When client registers a shipment of "Oranges" for a journey from Copenhagen to Hong Kong
		Then the journey is registered

	@tag2
	Scenario: No container available in the port of origin
		Given a Client "Andrei" with address "259 Lyngby" email "Andrei@roumania" and ref person "Yann"
		And the port of Copenhagen which has 0 containers
		And the port of Hong Kong which has 2 containers
		When client registers a shipment of "Oranges" for a journey from Copenhagen to Hong Kong
		Then the container is not registered for the journey