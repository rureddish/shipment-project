package CucumberJourney;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import dk.dtu.gbar.gitlab.shipment.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class JourneyStepDefinition {
	Client client;
	Location copenhagenLocation;
	Location hongKongLocation;
	Journey journey;
	JourneyList journeyList;
	ContainerList containerList;
	ContainerStatus containerStatus;
	//Feature : Search Journey    
    List<Journey> searchresult;
	
	public JourneyStepDefinition(JourneyList journeyList, ContainerList containerList) {
		this.journeyList = journeyList;
		this.containerList = containerList;
	}
	
/////////////////////////////////////////////////
//	Feature : Register Container for Journey
/////////////////////////////////////////////////

	@Given("a client with name {string}, address {string}, ref person {string} and email {string}")
	public void a_client_with_name_address_ref_person_and_email(String name, String address, String refPerson, String email) {
		client = new Client(name, address, refPerson, email);
		assertEquals(client.getName(),name);
		assertEquals(client.getAddress(), address);
		assertEquals(client.getRefPerson(), refPerson);
		assertEquals(client.getEmail(), email);
	}

	@Given("the port of Copenhagen which has {int} containers")
	public void the_port_of_Copenhagen_which_has_containers(Integer containerNumber) {
		copenhagenLocation = new Location("Copenhagen", containerNumber, containerList);
	    assertEquals(copenhagenLocation.getPlaceName(),"Copenhagen");
	    assertTrue(copenhagenLocation.getPortContainers().size()==containerNumber);
	    assertEquals(copenhagenLocation.getPortContainers().peek().getLocation(), copenhagenLocation);
	    assertTrue(containerList.getList().containsValue(copenhagenLocation.getPortContainers().peek()));
	}

	@Given("the port of Hong Kong which has {int} containers")
	public void the_port_of_Hong_Kong_which_has_containers(Integer containerNumber) {
		hongKongLocation = new Location("Hong Kong", containerNumber, containerList);
	}

	@When("client registers a container of {string} for a journey from Copenhagen to Hong Kong")
	public void client_registers_a_container_of_for_a_journey_from_Copenhagen_to_Hong_Kong_with_the_company(String content) {
		journey = new Journey(copenhagenLocation, hongKongLocation, client, content);
		journeyList.add(journey);	
		assertEquals(journey.getOrigin(),copenhagenLocation);
		assertEquals(journey.getDestination(), hongKongLocation);
		assertEquals(journey.getClient(), client);
		assertEquals(journey.getContainer().getContent(), content);
		assertEquals(journey.getContainer().getJourneyHistory().get(0), journey);
	}

	@Then("the container is registered for the journey")
	public void the_container_is_registered_for_the_journey() {
		assertTrue(journeyList.getList().containsValue(journey));
		assertFalse(copenhagenLocation.getPortContainers().contains(journey.getContainer()));
	}

///////////////////////////////////////////////
//	Feature : Journey Information Update
///////////////////////////////////////////////

	@Given("a journey from Copenhagen to Hong Kong, registered by the client for a container of {string}")
	public void a_journey_from_Copenhagen_to_Hong_Kong_registered_by_the_client_for_a_container_of(String content) {
		journey = new Journey(copenhagenLocation, hongKongLocation, client, content);
		journeyList.add(journey);
	}
	
	@Given("new container status with temp {int}, humidity {int}, pressure {int}")
	public void new_container_status_with_temp_humidity_pressure(Integer temp, Integer humidity, Integer pressure) {
		containerStatus = new ContainerStatus(temp, humidity, pressure);
		assertTrue(containerStatus.getTemp()==temp);
		assertTrue(containerStatus.getHumidity()==humidity);
		assertTrue(containerStatus.getPressure()==pressure);
	}

	@When("the user update the container information")
	public void the_user_update_the_container_information() {
		journey.updateContainerStatus(containerStatus);
	}

	@Then("the container information are updated")
	public void the_container_information_are_updated() {
		assertEquals(journey.getContainerStatusHistory().peek(), containerStatus);
	}
	
////////////////////////////////////////////////
//	Feature : Search Journey
////////////////////////////////////////////////
	
	@Given("that the journey is concluded")
	public void that_the_journey_is_concluded() {
	    journey.setJourneyStatus(JourneyStatus.CONCLUDED);
	    assertEquals(journey.getJourneyStatus(),JourneyStatus.CONCLUDED);
	}
	
	@When("searching for concluded journeys")
    public void searchingForConcludedJourneys() {
//		searchresult = (journeyList.search(journeyList.originContains(hongkong.getPlaceName())));
		searchresult = journeyList.search(journeyList.excludeCurrentJourneys);
	}

    @Then("return the concluded journey")
    public void returnTheConcludedJourney() {
        assertEquals(journey,searchresult.get(0));
    }

	
}
