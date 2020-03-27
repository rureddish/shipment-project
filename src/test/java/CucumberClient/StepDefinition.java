package CucumberClient;

import dk.dtu.gbar.gitlab.shipment.Client;
import dk.dtu.gbar.gitlab.shipment.Database;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import javax.xml.crypto.Data;

public class StepDefinition {
    Client properclient = new Client();
    Database clientsandcontainers = new Database();
    Client incompleteClient  = new Client();

    @Given("a Client {string} with {string}, {string} and {string}")
    public void aClientWithAnd(String clientname, String address, String referenceperson, String email) {
        properclient.setName(clientname);
        properclient.setAddress(address);
        properclient.setEmail(email);
        properclient.setRefPerson(referenceperson);
    }

    @And("a database not containing the client {string}")
    public void aDatabaseWithoutTheClient(String clientname) {
    }

    @When("{string} is registered")
    public void isRegistered(String arg0) throws Database.incompleteClientError, Database.clientAlreadyExistsError {
        clientsandcontainers.addClient(properclient);
    }

    @Then("{string} appears in database")
    public void appearsInDatabase(String clientname) {
        clientsandcontainers.searchClient(clientname).contains(properclient);
    }

    @Given("a Client {string} that is missing info")
    public void aClientThatIsMissingInfo(String clientname) {
        incompleteClient.setName(clientname);
    }

    @And("a database that already contains a client {string}")
    public void aDatabaseThatAlreadyContainsAClient(String clientname) throws Database.incompleteClientError, Database.clientAlreadyExistsError {
        clientsandcontainers.addClient(properclient);
    }

    @Then("error message incompleteclient is displayed")
    public void errorMessageIncompleteclientIsDisplayed() throws Database.incompleteClientError {
        System.out.println("Client could not be registered, info is missing");
    }

    @Then("error message clientAlreadyRegistered is displayed")
    public void errorMessageClientAlreadyRegisteredIsDisplayed() throws Database.clientAlreadyExistsError {
        System.out.println("Client could not be registered, a client by this name already exists");
    }

    @When("client {string} has their address updated to {string}")
    public void clientHasTheirAddressUpdatedTo(String clientname, String newaddress) {
    clientsandcontainers.searchClient(clientname).get(0).setAddress(newaddress);
    }

    @Then("the address of client {string} is now {string}")
    public void theAddressOfClientIsNow(String clientname, String newaddress) {
    }
}
