package CucumberJourney;

import static org.junit.Assert.assertEquals;

import dk.dtu.gbar.gitlab.shipment.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {
    Client client;
    ClientList clientList;
    Container container;
    Journey journey;
    JourneyList journeyList = new JourneyList();
    Port port1;
    Port port2;


    @Given("a client with name {string}, address {string}, ref person {string} and email {string}")
    public void a_client_with_name_address_ref_person_and_email(String name, String address, String refPerson, String email) {
        client = new Client(name, address, refPerson, email);
        clientList = new ClientList();
        clientList.add(client);
    }

    @Given("an unused container in {string}")
    public void an_unused_container_in(String origin) {
        port1 = new Port(origin);
        container = new Container(port1);
    }

    @When("client registers a container of {string} for a journey to {string} with the company")
    public void client_registers_a_container_of_for_a_journey_from_to_with_the_company(String content, String destination) {
        port2 = new Port(destination);
        journey = new Journey(port1, port2, content, client);
        journeyList.add(journey);
    }

    @Then("the container is registered for the journey")
    public void the_container_is_registered_for_the_journey() {
    	assertEquals(journey.getContainer(), container);
    }



}
