package CucumberClient;

import dk.dtu.gbar.gitlab.shipment.Client;
import dk.dtu.gbar.gitlab.shipment.Database;
import dk.dtu.gbar.gitlab.shipment.ResponseObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.xml.crypto.Data;

import static org.junit.Assert.assertEquals;

public class StepDefinition {
    Client properclient = new Client();
    Database clientsandcontainers;
    Client incompleteClient;
    ResponseObject response;

    @Given("a Client with all info filled in")
    public void aClientWithAllInfoFilledIn() {
        properclient.setName("clientname");
        properclient.setAddress("address");
        properclient.setEmail("email");
        properclient.setRefPerson("referenceperson");
    }

    @And("a database not containing the client")
    public void aDatabaseWithoutTheClient() {
        clientsandcontainers = new Database();
    }

    @When("the client is registered")
    public void isRegistered() throws Database.incompleteClientError, Database.clientAlreadyExistsError {
        response = clientsandcontainers.addClient(properclient);
    }

    @Then("the client appears in database")
    public void appearsInDatabase() {
        assertEquals(response.getErrorMessage(),"client added");
    }

    @Given("a Client that is missing info")
    public void aClientThatIsMissingInfo() {
        incompleteClient  = new Client();
        incompleteClient.setName("incomplete client");
    }

    @And("a database that contains the client")
    public void aDatabaseThatAlreadyContainsAClient() throws Database.incompleteClientError, Database.clientAlreadyExistsError {
        clientsandcontainers = new Database();
        clientsandcontainers.addClient(properclient);
    }

    @Then("error message incompleteclient is displayed")
    public void errorMessageIncompleteclientIsDisplayed() {
        assertEquals(response.getErrorMessage(),"client info missing" );
    }

    @Then("error message clientAlreadyRegistered is displayed")
    public void errorMessageClientAlreadyRegisteredIsDisplayed() {
        assertEquals(response.getErrorMessage(),"client already exists");
    }

    @When("the client has their address updated")
    public void clientHasTheirAddressUpdatedTo() {
    response = properclient.setAddress("newaddress");
    }

    @Then("the address of the client is now the new address")
    public void theAddressOfClientIsNow() {
        assertEquals(response.getErrorMessage(), "address updated");
    }

    @When("the client is removed")
    public void theClientIsRemoved() {
        response = clientsandcontainers.remove(properclient);
    }

    @Then("display message saying client has been removed")
    public void displayMessageSayingClientHasBeenRemoved() {
        assertEquals(response.getErrorMessage(),"client removed");
    }
}
