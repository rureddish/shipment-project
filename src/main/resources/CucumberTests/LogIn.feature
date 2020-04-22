
@tag
Feature: Client log in
 

  @tag1
  Scenario: Successful client log in 
    Given a Client "client" with address "address", ref person "refperson", email "email" and password "password" 
    And a password "password"
    And an email "email"
    When client logs in 
    Then client is logged in 

    
     @tag2
  Scenario: Wrong email
		Given a Client "client" with address "address", ref person "refperson", email "email" and password "password" 
    And a password "password"
    And an email "wrongEmail"
    When client logs in 
    Then client is not logged in 

     @tag3
  Scenario: Wrong password
    Given a Client "client" with address "address", ref person "refperson", email "email" and password "password"
    And a password "wrongPassword"
    And an email "email"
    When client logs in 
    Then client is not logged in

   Scenario: Log out
     Given a Client "client" with address "address", ref person "refperson", email "email" and password "password"
     And a password "password"
     And an email "wrongEmail"
     And client logs in
     And client logs out
     Then client is not logged in
