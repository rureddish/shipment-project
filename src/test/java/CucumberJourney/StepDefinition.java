package CucumberJourney;


import static org.junit.Assert.assertEquals;

import dk.dtu.gbar.gitlab.shipment.Client;
import dk.dtu.gbar.gitlab.shipment.Container;
import dk.dtu.gbar.gitlab.shipment.ContainerList;
import dk.dtu.gbar.gitlab.shipment.ContainerStatus;
//import dk.dtu.gbar.gitlab.shipment.Database;
import dk.dtu.gbar.gitlab.shipment.Journey;
import dk.dtu.gbar.gitlab.shipment.JourneyList;
import dk.dtu.gbar.gitlab.shipment.Port;
//import dk.dtu.gbar.gitlab.shipment.ResponseObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {
	Client client;
	Port copenhagenPort;
	Port hongKongPort;
	Container container;
	Journey journey;
	JourneyList journeyList;
	ContainerList containerList;
	ContainerStatus containerStatus;
	
	Double temp;
	Double humidity;
	Double pressure;
	Double time;
	String position;
	    
/////////////////////////////////////////////////
//	Feature : Register Container for Journey
/////////////////////////////////////////////////
	
	@Given("a client with name {string}, address {string}, ref person {string} and email {string}")
	public void a_client_with_name_address_ref_person_and_email(String name, String address, String refPerson, String email) {
		client = new Client(name, address, refPerson, email);
	}

	@Given("the port of Copenhagen")
	public void the_port_of_Copenhagen() {
	    copenhagenPort = new Port("Copenhagen");
	}
	
	@Given("the port of Hong Kong")
	public void the_port_of_Hong_Kong() {
		hongKongPort = new Port("Hong Kong");
	}
	
	@Given("a container not registered and located at Copenhagen")
	public void a_container_not_registered_and_located_at_Copenhagen() {
	    container = new Container(copenhagenPort);
	}
	
	@When("client registers a container of {string} for a journey from Copenhagen to Hong Kong")
	public void client_registers_a_container_of_for_a_journey_from_Copenhagen_to_Hong_Kong_with_the_company(String content) {
		container.setContent("Oranges");
		journey = new Journey(copenhagenPort, hongKongPort, client, container);
		container.newJourney(journey);
		
		journeyList = new JourneyList();
		containerList = new ContainerList();
		journeyList.add(journey);
		containerList.add(container);
	}
	
	@Then("the container is registered for the journey")
	public void the_container_is_registered_for_the_journey() {
		assertEquals(journey.getContainer(), container);
		assertEquals(container.getJourneyHistory().peek(), journey);
		assertEquals(journey.getClient(), client);
		assertEquals(journeyList.getList().get(journey.getID()), journey);
		assertEquals(containerList.getList().get(container.getID()), container);
	}
	
///////////////////////////////////////////////
//	Feature : Journey Information Update
///////////////////////////////////////////////
	
	@Given("a shipper")
	public void a_shipper() {
	}

	@Given("a container of {string}, located at Copenhagen and registered by the client for a journey from Copenhagen to Hong Kong")
	public void a_container_of_located_at_Copenhagen_and_registered_by_the_client_for_a_journey_from_Copenhagen_to_Hong_Kong(String string) {
		container = new Container(copenhagenPort);
		container.setContent("Oranges");
		journey = new Journey(copenhagenPort, hongKongPort, client, container);
		container.newJourney(journey);
		
		journeyList = new JourneyList();
		containerList = new ContainerList();
		journeyList.add(journey);
		containerList.add(container);
	}

	@Given("new information on the journey")
	public void new_information_on_the_journey() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@When("the user update the journey information")
	public void the_user_update_the_journey_information() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	@Then("the journey information are updated")
	public void the_journey_information_are_updated() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new cucumber.api.PendingException();
	}

	
}
