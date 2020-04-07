package CucumberJourney;

import static org.junit.Assert.assertEquals;

import java.util.List;

//import dk.dtu.gbar.gitlab.shipment.Database;
import dk.dtu.gbar.gitlab.shipment.*;
import io.cucumber.java.en.And;
//import dk.dtu.gbar.gitlab.shipment.ResponseObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinition {
	Client client;
	Location copenhagenLocation;
	Location hongKongLocation;
	Container container;
	Journey journey;
	JourneyList journeyList = new JourneyList();
	ContainerList containerList = new ContainerList();
	ContainerStatus containerStatus;
	
//Feature : Search Journey    
	Journey journey1;
    Journey journey2;
    Container container1;
    Container container2;
    List<Journey> searchresult;



/////////////////////////////////////////////////
//	Feature : Register Container for Journey
/////////////////////////////////////////////////

	@Given("a client with name {string}, address {string}, ref person {string} and email {string}")
	public void a_client_with_name_address_ref_person_and_email(String name, String address, String refPerson, String email) {
		client = new Client(name, address, refPerson, email);
	}

	@Given("the port of Copenhagen")
	public void the_port_of_Copenhagen() {
	    copenhagenLocation = new Location("Copenhagen");
	}

	@Given("the port of Hong Kong")
	public void the_port_of_Hong_Kong() {
		hongKongLocation = new Location("Hong Kong");
	}

	@Given("a container located at Copenhagen")
	public void a_container_not_registered_and_located_at_Copenhagen() {
		container = new Container(copenhagenLocation);
	    containerList.add(container);
	}

	@When("client registers a container of {string} for a journey from Copenhagen to Hong Kong")
	public void client_registers_a_container_of_for_a_journey_from_Copenhagen_to_Hong_Kong_with_the_company(String content) {
		journey = new Journey(copenhagenLocation, hongKongLocation, client, content);
		journeyList.add(journey);	
	}

	@Then("the container is registered for the journey")
	public void the_container_is_registered_for_the_journey() {
		assertEquals(journey.getContainer(), container);
		assertEquals(journey.getClient(), client);
		assertEquals(journeyList.getList().get(journey.getID()), journey);
		assertEquals(containerList.getList().get(container.getID()), container);
	}

///////////////////////////////////////////////
//	Feature : Journey Information Update
///////////////////////////////////////////////

	@Given("a container of {string}, located at Copenhagen and registered by the client for a journey from Copenhagen to Hong Kong")
	public void a_container_of_located_at_Copenhagen_and_registered_by_the_client_for_a_journey_from_Copenhagen_to_Hong_Kong(String content) {
		container = new Container(copenhagenLocation);
		containerList.add(container);
		journey = new Journey(copenhagenLocation, hongKongLocation, client, content);
		journeyList.add(journey);
	}

	@Given("new container status : temp {int}, humidity {int}, pressure {int}")
	public void new_container_status_temp_humidity_pressure(Integer temp, Integer humidity, Integer pressure) {
	    containerStatus = new ContainerStatus(temp, humidity, pressure);  
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
	
	@Given("{int} containers located at Copenhagen")
	public void containers_located_at_Copenhagen(Integer int1) {
	    container1 = new Container(copenhagenLocation);
	    container2 = new Container(copenhagenLocation);
	    containerList.add(container1);
	    containerList.add(container2);
	}
	
	@Given("a journey from Copenhagen to Hong Kong, registered by the client for a container of {string} and with status in progress")
	public void a_journey_from_Copenhagen_to_Hong_Kong_registered_by_the_client_for_a_container_of_and_with_status_in_progress(String content) {
		journey1 = new Journey(copenhagenLocation, hongKongLocation, client, content);
		journeyList.add(journey1);
	}

	@Given("a journey from Copenhagen to Hong Kong, registered by the client for a container of {string} and with status concluded")
	public void a_journey_from_Copenhagen_to_Hong_Kong_registered_by_the_client_for_a_container_of_and_with_status_concluded(String content) {
		journey2 = new Journey(copenhagenLocation, hongKongLocation, client, content);
		journey2.arrivalAtPort(hongKongLocation);
		journeyList.add(journey2);
	}

	@When("searching for concluded journeys")
    public void searchingForConcludedJourneys() {
//		searchresult = (journeyList.search(journeyList.originContains(hongkong.getPlaceName())));
		searchresult = journeyList.search(journeyList.excludeCurrentJourneys);
	}

	    @Then("return the concluded journey")
	    public void returnTheConcludedJourney() {
	        assertEquals(journey2,searchresult.get(0));
	    }

	
}
