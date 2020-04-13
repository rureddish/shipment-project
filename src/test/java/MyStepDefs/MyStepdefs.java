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

    Location copenhagen = new Location("Copenhagen");
    Location hongKong = new Location("Hong Kong");
    Container container1 = new Container(hongKong);
    Container container2 = new Container(hongKong);
    String errorMessage;
    Journey journey1;
    Journey journey2;
    JourneyList journeyList = new JourneyList();
    EntityList<Container> containerList = new EntityList<>();
    EntityList<Client> clientList = new EntityList<>();
    Searcher<? extends Entity> search = new Searcher<>();
    List<? extends Entity> searchresults;

    ////////////////////
    // register client
    ///////////////////
    @Given("a Client {string} with address {string} email {string} and ref person {string}")
    public void aClientWithAddressEmailAndRefPerson(String name, String address, String email, String refperson) {
        client = new Client(name, address, email, refperson);
    }

    @And("an empty client list")
    public void anEmptyClientList() {
        clientList = new EntityList<>();
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

    @Given("the port of Copenhagen")
    public void the_port_of_Copenhagen() {
        copenhagen = new Location("Copenhagen");
    }

    @Given("the port of Hong Kong")
    public void the_port_of_Hong_Kong() {
        hongKong = new Location("Hong Kong");
    }

    @Given("a container located at Copenhagen")
    public void a_container_located_at_Copenhagen() {
        container1 = new Container(copenhagen);
        containerList.add(container1);
    }

    @When("client registers a container of {string} for a journey from Copenhagen to Hong Kong")
    public void client_registers_a_container_of_for_a_journey_from_Copenhagen_to_Hong_Kong_with_the_company(String content) {
            journey1 = new Journey(copenhagen, hongKong, client, content);
            journeyList.add(journey1);
    }

    @Then("the container is registered for the journey")
    public void the_container_is_registered_for_the_journey() {
        assertEquals(journey1.getContainer(), container1);
        assertEquals(journey1.getClient(), client);
        assertTrue(journeyList.getList().contains(journey1));
        assertTrue(containerList.getList().contains(container1));
    }

///////////////////////////////////////////////
//	Feature : Journey Information Update
///////////////////////////////////////////////

    @And("a registered container in Copenhagen")
    public void aContainerInCopenhagen() {
        container1 = new Container(copenhagen);
        containerList.add(container1);
    }

    @And("a registered journey from Copenhagen to Hong Kong with {string}")
    public void aRegisteredJourneyFromCopenhagenToHongKongWith(String cargo) {
        journey1 = new Journey(copenhagen, hongKong, client, cargo);
        journeyList = new JourneyList();
        journeyList.add(journey1);
    }

    @When("the journey is begun")
    public void theJourneyIsBegun() {
        journey1.embark();
    }

    @Then("the container is at sea")
    public void theContainerIsAtSea() {
        assertEquals("At sea", container1.getLocation().getPlaceName());
    }

//////////////////////////////
///// search for concluded journeys
//////////////////////////////

    @Given("a journey in progress")
    public void aJourneyInProgress() {
        journey1 = new Journey(hongKong,copenhagen, client, "cheap toys");
        journeyList.add(journey1);

    }

    @And("a concluded journey")
    public void aConcludedJourney() {
        journey2 = new Journey(hongKong,copenhagen, client, "cheap toys");
        journeyList.add(journey2);
        journey2.embark();
        journey2.arrive();
    }

    @When("searching for concluded journeys")
    public void searchingForConcludedJourneys() {
        searchresults = search.search(journeyList.getList(), search.excludeCurrentJourneys);
    }

    @Then("return the concluded journey")
    public void returnTheConcludedJourney() {
        assertTrue(searchresults.contains(journey2));
    }

    @When("searching for current journeys")
    public void searching_for_current_journeys() {
        searchresults = search.search(journeyList.getList(),search.excludeConcludedJourneys);
    }

    @Then("return the current journey")
    public void return_the_current_journey() {
        assertEquals(journey1,searchresults.get(0));
    }

    //////Scenario3
    @When("client searches for journeys coming from {string}")
    public void searches_for_journeys_coming_from(String origin) {
        searchresults = search.search(journeyList.getList(), search.originContains(origin));
    }

    @Then("return the journey coming from Copenhagen")
    public void return_the_journey_coming_from_Copenhagen() {
        assertEquals(journey1,searchresults.get(0));
    }

    //////Scenario4
    @When("client searches for journeys bound for {string}")
    public void client_searches_for_journeys_bound_for(String destination) {
        searchresults = search.search(journeyList.getList(),search.destinationContains(destination));
    }

    @Then("return the journey bounds for Hong Kong")
    public void return_the_journey_bounds_for_Hong_Kong() {
        assertEquals(journey1,searchresults.get(0));
    }

    @And("the port of Copenhagen which has {int} containers")
    public void thePortOfCopenhagenWhichHasContainers(int numberOfContainers) {
        copenhagen = new Location("Copenhagen", numberOfContainers, containerList);
    }

    @And("the port of Hong Kong which has {int} containers")
    public void thePortOfHongKongWhichHasContainers(int numberOfContainers) {
        hongKong = new Location("Hong Kong", numberOfContainers, containerList);
    }


    @Then("the container is not registered for the journey")
    public void theContainerIsNotRegisteredForTheJourney() {
        assertFalse(journeyList.getList().contains(journey1));
    }
}
