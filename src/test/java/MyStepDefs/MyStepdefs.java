package MyStepDefs;

import dk.dtu.gbar.gitlab.shipment.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.*;

public class MyStepdefs {

    Client client;
    Journey journey1;
    Journey journey2;
    Location copenhagen = new Location("Copenhagen");
    Location hongKong = new Location("Hong Kong");
    Container container1 = new Container(hongKong);
    Container container2 = new Container(hongKong);
    Ship ship;

    LogisticsCompany logisticCompany = new LogisticsCompany("Logistic Company", "address", "refPerson", "email", "admin");
    Searcher<? extends Entity> search = new Searcher<>();
    List<? extends Entity> searchresults;

    
    String passwordTest;
    String emailTest;
    LogIn logIn = new LogIn(logisticCompany.getClientList());

    ////////////////////
    // register client
    ///////////////////
    @Given("a Client {string} with address {string} email {string} and ref person {string}")
    public void aClientWithAddressEmailAndRefPerson(String name, String address, String email, String refperson) {
        client = new Client(name, address, email, refperson, "password");
    }

    @And("an empty client list")
    public void anEmptyClientList() {
        assertTrue(logisticCompany.getClientList().isEmpty());
    }

    @When("the client is registered")
    public void theClientIsRegistered() {
        logisticCompany.register(client);
    }

    @Then("the client list contains the client")
    public void theClientListContainsClient() {
        assertTrue(logisticCompany.getClientList().contains(client));
    }

///////////////////////////
//client info update
///////////////////////////

    @And("client list containing the client")
    public void clientListContainingTheClient() {
        logisticCompany.register(client);
    }

    @When("the info of the client is updated")
    public void theInfoOfTheClientIsUpdated() {
        client.setAddress("new address");
        client.setName("new name");
        client.setEmail("new email");
        client.setRefPerson("new refperson");
    }

    @Then("the client now has new info")
    public void theClientNowHasNewInfo() {
        assertEquals("new address", client.getAddress());
        assertEquals("new name", client.getName());
        assertEquals("new email", client.getEmail());
        assertEquals("new refperson", client.getRefPerson());
    }

///////////////////////////
// searching client
///////////////////////////
    @When("searching clients by name {string}")
    public void searchingClientsByName(String name) {
        searchresults = search.search(logisticCompany.getClientList(), search.clientNameContains(name));
    }

    @Then("the client appears in search results")
    public void theClientAppearsInSearchResults() {
        assertEquals(client, searchresults.get(0));
    }

    @When("searching clients by address {string}")
    public void searchingClientsByAddress(String address) {
        searchresults = search.search(logisticCompany.getClientList(), search.addressContains(address));
    }

    @When("searching clients for string {string}")
    public void searchingClientsForString(String string) {
        searchresults = search.clientSearchByString(logisticCompany.getClientList(),string);
    }

///////////////
// remove client
///////////////
    @When("the client is removed")
    public void theClientIsRemoved() {
        logisticCompany.getClientList().remove(client);
    }

    @Then("the client list does not contain the client")
    public void theClientListDoesNotContainTheClient() {
        assertFalse(logisticCompany.getClientList().contains(client));
    }

/////////////////////////////////////////////////
//	Feature : Register Container for Journey
/////////////////////////////////////////////////

    ////Scenario 1
    @Given("the port of Copenhagen which has {int} containers")
    public void thePortOfCopenhagenWhichHasContainers(int numberOfContainers) {
        copenhagen = new Location("Copenhagen", numberOfContainers, logisticCompany);
    }

    @And("the port of Hong Kong which has {int} containers")
    public void thePortOfHongKongWhichHasContainers(int numberOfContainers) {
        hongKong = new Location("Hong Kong", numberOfContainers, logisticCompany);
    }

    @When("client registers a shipment of {string} for a journey from Copenhagen to Hong Kong")
    public void client_registers_a_shipment_of_for_a_journey_from_Copenhagen_to_Hong_Kong_with_the_company(String content) {
        journey1 = new Journey(copenhagen, hongKong, client, content);
        logisticCompany.register(journey1);
    }

    @Then("the journey is registered")
    public void the_shipment_is_registered_for_the_journey() {
        assertEquals(journey1.getClient(), client);
        assertTrue(logisticCompany.getJourneyList().contains(journey1));
        assertTrue(logisticCompany.getContainerList().contains(journey1.getContainer()));
    }

    ////Scenario 2
    @Then("the container is not registered for the journey")
    public void theContainerIsNotRegisteredForTheJourney() {
        assertFalse(container1.getJourneyHistory().contains(journey1));
    }


///////////////////////////////////////////////
//	Feature : Journey Information Update
///////////////////////////////////////////////

    ///Scenario 1
    @And("a registered journey from Copenhagen to Hong Kong with {string}")
    public void aRegisteredJourneyFromCopenhagenToHongKongWith(String cargo) {
        journey1 = new Journey(copenhagen, hongKong, client, cargo);
        logisticCompany.register(journey1);
    }

    @Given("a registered ship in Copenhagen")
    public void a_ship_with_ID_in_Copenhagen() {
        ship = new Ship(copenhagen, logisticCompany);
        logisticCompany.register(ship);
    }

    @And("the container is added to the ship")
    public void theContainerIsAddedToTheShip() {
        ship.getContainers().add(journey1.getContainer());
    }

    @When("the worker informs of the departure of the ship transporting the container")
    public void the_worker_informs_of_the_departure_of_the_ship_transporting_the_container() {
    	logisticCompany.register(ship);
        ship.depart();
    }

    @Then("the ship and the container are at sea")
    public void the_ship_and_the_container_are_at_sea() {
        assertEquals(ship.getLocation().getPlaceName(), "At sea");
        assertEquals(ship.getContainers().get(0), journey1.getContainer());
    }

//////////////////////////////
///// search for journeys
//////////////////////////////

    //Scenario 1
    @Given("a journey in progress")
    public void aJourneyInProgress() {
        journey1 = new Journey(hongKong,copenhagen, client, "oranges");
        logisticCompany.register(journey1);
    }

    @And("a concluded journey")
    public void aConcludedJourney() {
        journey2 = new Journey(hongKong,copenhagen, client, "oranges");
        logisticCompany.register(journey2);
        journey2.endJourney();
    }

    @When("searching for concluded journeys")
    public void searchingForConcludedJourneys() {
        searchresults = client.getConcludedJourneys();
    }

    @Then("return the concluded journey")
    public void returnTheConcludedJourney() {
        assertTrue(searchresults.contains(journey2));
        assertFalse(searchresults.contains(journey1));
    }

//    Scenario 2
    @When("searching for current journeys")
    public void searching_for_current_journeys() {
        searchresults = client.getCurrentJourneys();
    }

    @Then("return the current journey")
    public void return_the_current_journey() {
        assertTrue(searchresults.contains(journey1));
        assertFalse(searchresults.contains(journey2));
    }

    //////Scenario3
    @When("client searches for journeys coming from {string}")
    public void searches_for_journeys_coming_from(String origin) {
        searchresults = search.search(logisticCompany.getJourneyList(), search.originContains(origin));
    }

    @Then("return the journey coming from Copenhagen")
    public void return_the_journey_coming_from_Copenhagen() {
        assertEquals(journey1,searchresults.get(0));
    }

    //////Scenario4
    @When("client searches for journeys bound for {string}")
    public void client_searches_for_journeys_bound_for(String destination) {
        searchresults = search.search(logisticCompany.getJourneyList(),search.destinationContains(destination));
    }

    @Then("return the journeys bound for Hong Kong")
    public void return_the_journey_bounds_for_Hong_Kong() {
        assertEquals(journey1,searchresults.get(0));
    }

////////////////////////////
///	Feature : Log In
////////////////////////////
    
    //Scenario 1
    @Given("a Client {string} with address {string}, ref person {string}, email {string} and password {string}")
    public void a_Client_with_address_ref_person_email_and_password(String name, String address, String refPerson, String email, String password) {
    	client = new Client(name, address, refPerson, email, password);
        logisticCompany.register(client);
    }
    
    @Given("a password {string}")
    public void a_password(String password) {
        passwordTest = password;
    }

    @Given("an email {string}")
    public void an_email(String email) {
        emailTest = email;
    }

    @When("client logs in")
    public void client_logs_in() {
        logIn.logIn(emailTest, passwordTest);
    }

    @Then("client is logged in")
    public void client_is_logged_in() {
        assertEquals(logIn.getLoggedInClient(), client);
    }
    
    //Scenario 2
    @Then("client is not logged in")
    public void client_is_not_logged_in() {
        assertEquals(logIn.getLoggedInClient(), null);
    }
    
    //log out
    @And("client logs out")
    public void clientLogsOut() {
        logIn.logOut();
    }
}
