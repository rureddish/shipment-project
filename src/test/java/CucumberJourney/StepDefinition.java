package CucumberJourney;

import static org.junit.Assert.assertEquals;

import dk.dtu.gbar.gitlab.shipment.Container;
import dk.dtu.gbar.gitlab.shipment.Journey;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Client;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {
	Client client = new Client();
	Container container = new Container();	
	Journey journey = new Journey();
	
	@Given("a client")
	public void a_client() {
		
	    
	}

	@Given("a container with id {string}")
	public void a_container_with_id(String containerId) {
	    container.setContainerID(containerId);
	}

	@When("client creates journey for container with port of origin {string}, destination {string}, content {string}, company {string}")
	public void client_creates_journey_for_container_with_port_of_origin_destination_content_company(String portOfOrigin, String destination, String containerContent, String company) {
		journey.setPortOfOrigin(portOfOrigin);
		journey.setDestination(destination);
		journey.setCompany(company);
		container.setContent(containerContent);
		journey.setContainerID(container.getContainerID());
		container.setJourneyID(journey.getJourneyID());
	}

	@Then("a journey is created for the container with id {string}")
	public void a_journey_is_created_for_the_container_with_id(String string) {
	    assertEquals(journey.getContainerID(), container.getContainerID());
	}
	
	@Then("new journey id is created automatically")
	public void new_journey_id_is_created_automatically() {
	    
	}

	@When("client creates journey for container with port of origin {string}, destination {string}, content {string}")
	public void client_creates_journey_for_container_with_port_of_origin_destination_content(String string, String string2, String string3) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("error message is displayed")
	public void error_message_is_displayed() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}
}
