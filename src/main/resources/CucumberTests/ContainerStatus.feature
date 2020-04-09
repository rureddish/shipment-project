#Feature: Tracking Container
#
#	Scenario: successfully registering a container with all info
#		Given a Container with all info filled in
#		And a database not containing the Container
#		When the Container is registered
#		Then the Container appears in the database
#
#	Scenario: registering a container with info missing
#		Given a Container with info missing
#		And a database not containing a container
#		When the Container is registered
#		Then error incompleteContainer message is displayed
#
#	Scenario: registering a container already in database
#		Given a Container with all info filled in
#		And a database that contains the container
#		When the Container is registered
#		Then error containerExists message is displayed
#
#	Scenario: successfully registering a container for a journey
#		Given a registered Container
#		And a successfully registered Journey
#		When the Journey registers the Container
#		Then the Container is in the Journey
#
#	Scenario: successfully updating container information
#		Given a Container in a Journey
#		And a reader for the Container's info
#		When a time period has passed
#		Then the Container's lists register new data