package CucumberClient;

import dk.dtu.gbar.gitlab.shipment.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinition {
    ClientList clientList = new ClientList();
    ContainerList containerList = new ContainerList();
    JourneyList journeyList = new JourneyList();
    Client client;
    Container container;
    Container othercontainer;
    ArrayList searchresult;

    @Given("a Client {string} with all info filled in")
    public void aClientWithAllInfoFilledIn(String client) {
        this.client = new Client(client, "address", "email", "referenceperson");
    }

    @And("a client list not containing the client")
    public void aClientListNotContainingTheClient() {
        clientList = new ClientList();
    }

    @When("the client is registered")
    public void isRegistered() {
        clientList.add(client);
    }

    @Then("the client appears in database")
    public void appearsInDatabase() {
        assertEquals(true, clientList.getList().containsValue(client));
    }

    @Given("a Client {string} that is missing info")
    public void aClientThatIsMissingInfo(String client) {
        this.client = new Client(client, "address", "refperson","");
    }

    @And("a client list that contains the client")
    public void aDatabaseThatAlreadyContainsAClient() {
        clientList.add(client);
    }

    @Then("error message incompleteclient is displayed")
    public void errorMessageIncompleteclientIsDisplayed() {

    }

    @When("the client has their address updated")
    public void theClientHasTheirAddressUpdated() {
        client.setAddress("newaddress");
        client.setName("newname");
        client.setRefPerson("newperson");
        client.setEmail("newemail");
    }

    @Then("the address of the client is now the new address")
    public void theAddressOfTheClientIsNowTheNewAddress() {
        assertEquals("newaddress", client.getAddress());
        assertEquals("newname", client.getName());
        assertEquals("newperson", client.getRefPerson());
        assertEquals("newemail", client.getEmail());
    }

    @When("the client is removed")
    public void theClientIsRemoved() {
        clientList.remove(client);
    }

    @Then("the client list no longer contains client")
    public void theClientListNoLongerContainsClient() {
        assertEquals(false,clientList.getList().containsValue(client));
    }

    @When("the client list is searched for {string}")
    public void theClientListIsSearchedFor(String name) {
        searchresult = clientList.searchByString(name);
    }

    @Then("the client appears in search results")
    public void theClientAppearsInSearchResults() {
        assertEquals(true,searchresult.contains(client));
    }

    @When("the client list is searched for clients address")
    public void theClientListIsSearchedForClientsAddress() {
        searchresult = clientList.searchByString("address");
    }
}
