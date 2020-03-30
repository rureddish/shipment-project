package CucumberClient;

import dk.dtu.gbar.gitlab.shipment.Client;
import dk.dtu.gbar.gitlab.shipment.Container;
import dk.dtu.gbar.gitlab.shipment.Database;
import dk.dtu.gbar.gitlab.shipment.ResponseObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertEquals;

public class StepDefinition {
    Client verifiedClient = new Client();
    Database database;
    Client incompleteClient;
    ResponseObject response;
    Container container;
    Container othercontainer;

    @Given("a Client {string} with all info filled in")
    public void aClientWithAllInfoFilledIn(String client) {
        verifiedClient.setName(client);
        verifiedClient.setAddress("address");
        verifiedClient.setEmail("email");
        verifiedClient.setRefPerson("referenceperson");
    }

    @And("a database not containing the client")
    public void aDatabaseWithoutTheClient() {
        database = new Database();
    }

    @When("the client is registered")
    public void isRegistered() {
        response = database.getClientList().addClient(verifiedClient);
    }

    @Then("the client appears in database")
    public void appearsInDatabase() {
        assertEquals(response.getErrorMessage(), "client added");
    }

    @Given("a Client {string} that is missing info")
    public void aClientThatIsMissingInfo(String client) {
        incompleteClient = new Client();
        incompleteClient.setName(client);
    }

    @And("a database that contains the client")
    public void aDatabaseThatAlreadyContainsAClient() {
        database = new Database();
        database.getClientList().addClient(verifiedClient);
    }

    @Then("error message incompleteclient is displayed")
    public void errorMessageIncompleteclientIsDisplayed() {
        assertEquals(response.getErrorMessage(), "client info missing");
    }

    @Then("error message clientAlreadyRegistered is displayed")
    public void errorMessageClientAlreadyRegisteredIsDisplayed() {
        assertEquals(response.getErrorMessage(), "client already exists");
    }

    @When("the client has their address updated")
    public void clientHasTheirAddressUpdatedTo() {
        response = verifiedClient.setAddress("newaddress");
    }

    @Then("the address of the client is now the new address")
    public void theAddressOfClientIsNow() {
        assertEquals(response.getErrorMessage(), "address updated");
    }

    @When("the client is removed")
    public void theClientIsRemoved() {
        response = database.getClientList().remove(verifiedClient);
    }

    @Then("display message saying client has been removed")
    public void displayMessageSayingClientHasBeenRemoved() {
        assertEquals("client removed", response.getErrorMessage());
    }

    @When("the database is searched for the client")
    public void theDatabaseIsSearchedForTheClient() {
        response = database.getClientList().searchClient(verifiedClient.getName());
    }

    @Then("display the client info")
    public void displayTheClientInfo() {
        assertEquals(verifiedClient.getName(), response.getErrorMessage());
    }

    @Then("display message saying no clients found")
    public void displayMessageSayingClientNotFound() {
        assertEquals("No clients found", response.getErrorMessage());
    }

    @And("a container not used by a client")
    public void aContainerNotUsedByAnotherClient() {
        container = new Container();
    }

    @When("The container is registered for the client")
    public void theContainerIsRegisteredForTheClient() {
        response = verifiedClient.addContainer(container);
    }

    @Then("Display {string} registered for {string}")
        public void displayRegisteredFor(String ID, String client) {
    }

    @And("a container being used by another client")
    public void aContainerBeingUsedByAnotherClient() {
        container = new Container();
        container.setOwnerID("some owner");
    }


    @Then("Display container used by other client")
    public void displayContainerUsedByOtherClient() {
        assertEquals("Container already in use", response.getErrorMessage());
    }

    @Given("a container with id {string}")
    public void a_container_with_id(String containerId) {
        container = new Container();
        container.setContainerID(containerId);
    }

    @When("trying to register another container with id {string}")
    public void tryingToRegisterAContainerWithId(String id) {
        othercontainer = new Container();
        othercontainer.setOwnerID(id);
        database.getContainerList().addContainer(othercontainer);
        response = database.getContainerList().addContainer(othercontainer);
    }

    @Then("display a container with this id already exists")
    public void displayAContainerWithThisIdAlreadyExists() {
        assertEquals("Container with this id already exists", response.getErrorMessage());
    }
}
