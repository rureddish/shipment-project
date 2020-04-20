package MyStepDefs;

import dk.dtu.gbar.gitlab.shipment.*; 
import io.cucumber.java.af.En;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.Assert.*;

public class MyStepdefs {
    Client client;
    LogisticCompany logisticCompany = new LogisticCompany("Logistic Company", "address", "refPerson", "email");
    LogisticCompanyUser logisticCompanyUser;
    ClientUser user1;
    
    Location copenhagen = new Location("Copenhagen", 100, logisticCompany.getContainerList());
    Location hongKong = new Location("Hong Kong", 200, logisticCompany.getContainerList());
    Container container1 = new Container(hongKong);
    Container container2 = new Container(hongKong);
    String errorMessage;
    Journey journey1;
    Journey journey2;
    //---> the lists are now in the LogisticCompany object
    JourneyList journeyList = new JourneyList();
    EntityList<Container> containerList = new EntityList<>();
    ClientList clientList = new ClientList();
    Searcher<? extends Entity> search = new Searcher<>();
    Searcher<Container> searchContainer = new Searcher<>();
    List<? extends Entity> searchresults;
    Ship ship;

    ////////////////////
    // register client 
    ///////////////////
    @Given("a Client {string} with address {string} email {string} and ref person {string}")
    public void aClientWithAddressEmailAndRefPerson(String name, String address, String email, String refperson) {
        client = new Client(name, address, email, refperson);
        user1 = new ClientUser(client);
        user1.setJourneyList(logisticCompany.getJourneyList());
    }

    @And("an empty client list")
    public void anEmptyClientList() {
        clientList = new ClientList();
    }

    @When("the client is registered")
    public void theClientIsRegistered() {
        clientList.add(client);
    }

    @Then("the client list contains the client")
    public void theClientListContainsClient() {
        assertTrue(clientList.getList().contains(client));
    }

///////////////////////////
//client info update
///////////////////////////

    @And("client list containing the client")
    public void clientListContainingTheClient() {
        clientList.add(client);
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
        searchresults = search.search(clientList.getList(), search.clientNameContains(name));
    }

    @Then("the client appears in search results")
    public void theClientAppearsInSearchResults() {
        assertEquals(client, searchresults.get(0));
    }

    @When("searching clients by address {string}")
    public void searchingClientsByAddress(String address) {
        searchresults = search.search(clientList.getList(), search.addressContains(address));
    }

    @When("searching clients for string {string}")
    public void searchingClientsForString(String string) {
        searchresults = search.clientSearchByString(clientList.getList(),string);
    }

///////////////
// remove client
///////////////
    @When("the client is removed")
    public void theClientIsRemoved() {
        clientList.remove(client);
    }

    @Then("the client list does not contain the client")
    public void theClientListDoesNotContainTheClient() {
        assertFalse(clientList.getList().contains(client));
    }

/////////////////////////////////////////////////
//	Feature : Register Container for Journey
/////////////////////////////////////////////////
    
    ////Scenario 1    
    @Given("the port of Copenhagen which has {int} containers")
    public void thePortOfCopenhagenWhichHasContainers(int numberOfContainers) {
        copenhagen = new Location("Copenhagen", numberOfContainers, logisticCompany.getContainerList());
    }

    @And("the port of Hong Kong which has {int} containers")
    public void thePortOfHongKongWhichHasContainers(int numberOfContainers) {
        hongKong = new Location("Hong Kong", numberOfContainers, logisticCompany.getContainerList());
    }

    @When("client registers a container of {string} for a journey from Copenhagen to Hong Kong")
    public void client_registers_a_container_of_for_a_journey_from_Copenhagen_to_Hong_Kong_with_the_company(String content) {
        try {
        	journey1 = new Journey(copenhagen, hongKong, client, content);
            logisticCompany.getJourneyList().add(journey1);
    	} 
    	catch (ErrorException e) { 
    		errorMessage = e.getMessage();
    	}

    }
 
    @Then("the container is registered for the journey")
    public void the_container_is_registered_for_the_journey() {
//        assertEquals(journey1.getContainer(), container1);
        assertEquals(journey1.getClient(), client);
        assertTrue(logisticCompany.getJourneyList().getList().contains(journey1));
//        assertTrue(logisticCompany.getContainerList().getList().contains(journey1.getContainer()));
    }
    
    ////Scenario 2
    @Then("the container is not registered for the journey")
    public void theContainerIsNotRegisteredForTheJourney() {
        assertEquals(errorMessage, "No container available in the port of origin");
    }

///////////////////////////////////////////////
//	Feature : Journey Information Update
///////////////////////////////////////////////
    
    ///Scenario 1
    @Given("a worker of the logistic company")
    public void a_worker_of_the_logistic_company() {
        logisticCompanyUser = new LogisticCompanyUser(logisticCompany);
    }
    
    @Given("a registered journey from Copenhagen to Hong Kong with {string}")
    public void a_registered_journey_from_Copenhagen_to_Hong_Kong_with(String cargo) {
    	try {
        	journey1 = new Journey(copenhagen, hongKong, client, cargo);
        	logisticCompany.getJourneyList().add(journey1);
    	} 
    	catch (ErrorException e) { 
    		errorMessage = e.getMessage();
    	}
    }
    
    @Given("a ship with ID {int} in Copenhagen")
    public void a_ship_with_ID_in_Copenhagen(Integer ID) {
        ship = new Ship(ID, copenhagen);
    }

    @When("the worker informs of the embarkation of the ship transporting the container")
    public void the_worker_informs_of_the_embarkation_of_the_ship_transporting_the_container() {
    	logisticCompanyUser.informEmbarkation(ship, journey1.getContainer().getID());
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
    	try {
        	journey1 = new Journey(copenhagen, hongKong, client, "cheap toys");
        	logisticCompany.getJourneyList().add(journey1);
    	} 
    	catch (ErrorException e) { 
    		errorMessage = e.getMessage();
    	}
    }

    @And("a concluded journey")
    public void aConcludedJourney() {
    	try {
        	journey2 = new Journey(copenhagen, hongKong, client, "oranges");
        	logisticCompany.getJourneyList().add(journey2);
        	journey2.setConcluded();
    	} 
    	catch (ErrorException e) { 
    		errorMessage = e.getMessage();
    	}
    	
    }

    @When("searching for concluded journeys")
    public void searchingForConcludedJourneys() {
        searchresults = user1.getConcludedClientJourneys();
    }

    @Then("return the concluded journey")
    public void returnTheConcludedJourney() {
        assertTrue(searchresults.contains(journey2));
        assertFalse(searchresults.contains(journey1));
    }
    
//    Scenario 2
    @When("searching for current journeys")
    public void searching_for_current_journeys() {
        searchresults = user1.getCurrentClientJourneys();
    }

    @Then("return the current journey")
    public void return_the_current_journey() {
        assertTrue(searchresults.contains(journey1));
        assertFalse(searchresults.contains(journey2));
    }

    //////Scenario3
    @When("client searches for journeys coming from {string}")
    public void searches_for_journeys_coming_from(String origin) {
        searchresults = search.search(logisticCompany.getJourneyList().getList(), search.originContains(origin));
    }

    @Then("return the journey coming from Copenhagen")
    public void return_the_journey_coming_from_Copenhagen() {
        assertEquals(journey1,searchresults.get(0));
    }

    //////Scenario4
    @When("client searches for journeys bound for {string}")
    public void client_searches_for_journeys_bound_for(String destination) {
        searchresults = search.search(logisticCompany.getJourneyList().getList(),search.destinationContains(destination));
    }

    @Then("return the journey bounds for Hong Kong")
    public void return_the_journey_bounds_for_Hong_Kong() {
        assertEquals(journey1,searchresults.get(0));
    }
}
