Feature: Update Container Status
Actor: Worker of the Logistic Company

  @tag1
  Scenario: All Info Update
    Given a Client "Andrei" with address "259 Lyngby" email "Andrei@roumania" and ref person "Yann"
    And the port of Copenhagen which has 10 containers
    And a registered journey from Copenhagen to Hong Kong with "oranges"
    And the container has a temperature of "15", pressure of "1010" and humidity of "54"
    When a worker updates the container information
    Then the container information are updated
    And the date is automatically stored


    
