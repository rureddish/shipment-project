package CucumberClient;

import dk.dtu.gbar.gitlab.shipment.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class StepDefinition {
    ClientList clientList;
    Client aClient;
    List searchresult;
    JourneyList journeyList;
    Location hongkong = new Location("Hong Kong");
    Location copenhagen = new Location("Copenhagen");
    Journey journey1;
    Journey journey2;
    Container container1;
    Container container2;


    @Given("a Client {string} with address {string} email {string} and ref person {string}")
    public void aClientWithAddressRefPersonAndEmail(String client, String address, String email, String refperson) {
        aClient = new Client(client, address, email, refperson);
    }

    @And("a client list not containing the client")
    public void aClientListNotContainingTheClient() {
        clientList = new ClientList();
    }

    @When("the client is registered")
    public void isRegistered() {
        clientList.add(aClient);
    }

    @Then("the client appears in client list")
    public void appearsInDatabase() {
        assertTrue(clientList.getList().containsValue(aClient));
    }

    @And("a client list that contains the client")
    public void aDatabaseThatAlreadyContainsAClient() {
        clientList = new ClientList();
        clientList.add(aClient);
    }

    @Then("error message incompleteclient is displayed")
    public void errorMessageIncompleteclientIsDisplayed() {

    }

    @When("the client has their info updated")
    public void theClientHasTheirAddressUpdated() {
        aClient.setAddress("newaddress");
        aClient.setName("newname");
        aClient.setRefPerson("newperson");
        aClient.setEmail("newemail");
    }

    @Then("the info of the client is now the new address")
    public void theAddressOfTheClientIsNowTheNewAddress() {
        assertEquals("newaddress", aClient.getAddress());
        assertEquals("newname", aClient.getName());
        assertEquals("newperson", aClient.getRefPerson());
        assertEquals("newemail", aClient.getEmail());
    }

    @Then("client is not in client list")
    public void clientDoesNotAppearInClientList() {
        assertFalse(clientList.getList().containsValue(aClient.getID()));
    }

    @When("the client is removed")
    public void theClientIsRemoved() {
        clientList.remove(aClient);
    }

    @When("the list is searched for string {string}")
    public void theListIsSearchedForString(String string) {
        searchresult = clientList.searchByString(string);
    }

    @When("the list is searched for name {string}")
    public void theClientListIsSearchedFor(String name) {
        searchresult = clientList.search(clientList.nameContains(name));
    }

    @Then("the client appears in search results")
    public void theClientAppearsInSearchResults() {
        assertTrue(searchresult.contains(aClient));
    }

    @When("the client list is searched for clients address")
    public void theClientListIsSearchedForClientsAddress() {
        searchresult = clientList.searchByString("address");
    }

    @Given("a journey list")
    public void aJourneyList(){
        journeyList = new JourneyList();
    }

    @And("a journey in progress")
    public void aJourneyInProgress() {
        Journey journey1 = new Journey(hongkong, copenhagen, aClient, "cheese");
        journeyList.add(journey1);
    }

    @And("two containers located at the origin")
    public void twoContainersLocatedAtTheOrigin() {
        container1 = new Container(hongkong);
        container2 = new Container(hongkong);
    }

    @And("a concluded journey")
    public void aConcludedJourney() {
        journey2 = new Journey(hongkong, copenhagen, aClient, "cheese");
        journeyList.add(journey2);
        journey2.embark();
        journey2.arrive();
    }


    @When("searching for concluded journeys")
    public void searchingForConcludedJourneys() {
        searchresult = (journeyList.search(journeyList.originContains(hongkong.getPlaceName())));
        searchresult = journeyList.filterBy(searchresult, journeyList.excludeCurrentJourneys);
    }

    @Then("return the concluded journey")
    public void returnTheConcludedJourney() {
        assertEquals(journey2,searchresult.get(0));
    }


}
