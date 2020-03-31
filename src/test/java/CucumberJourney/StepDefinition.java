package CucumberJourney;

import static org.junit.Assert.assertEquals;

import dk.dtu.gbar.gitlab.shipment.Container;
import dk.dtu.gbar.gitlab.shipment.Database;
import dk.dtu.gbar.gitlab.shipment.Journey;
import dk.dtu.gbar.gitlab.shipment.ResponseObject;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Client;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {
	Client client;
	Container container;
	Journey journey;
	Database database;
	ResponseObject response;
	
	
	@Given("a client with name {string}, address {string}, ref person {string} and email {string}")
	public void a_client_with_name_address_ref_person_and_email(String name, String address, String refPerson, String email) {
		client = new Client(name, address, refPerson, email);
	}

	@Given("an empty container")
	public void an_empty_container() {
	    container = new Container();
	}
	
	@Given("a database not containing the journey and the container")
	public void a_database_not_containing_the_journey_and_the_container() {
	    database = new Database();
	}

	@When("client registers a container of {string} for a journey from {string} to {string} with the company {string}")
	public void client_registers_a_container_of_for_a_journey_from_to_with_the_company(String content, String portOfOrigin, String portOfDestination, String company) {
		journey = new Journey(portOfOrigin, portOfDestination, company, container);
		container.setContent(content);
		container.setJourney(journey);
		//For the feature with the database
		response = database.getJourneyList().addJourney(journey);
	}
	
	@Then("the container is registered for the journey")
	public void the_container_is_registered_for_the_journey() {
		assertEquals(journey.getContainer(), container);
		assertEquals(container.getJourney(), journey);
	}

	@Then("the journey that contains the container is stored in the database")
	public void the_journey_that_contains_the_container_is_stored_in_the_database() {
	//	assertEquals(database.getJourneyList().searchJourney(journey.getJourneyID()),journey.getJourneyID());
	}

	@Then("a message SuccessfulRegistration is displayed")
	public void a_message_SuccessfulRegistration_is_displayed() {
		assertEquals(response.getErrorMessage(), "Journey added");
	}

	
	
}
