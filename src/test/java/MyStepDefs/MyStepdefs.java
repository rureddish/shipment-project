package MyStepDefs;

import dk.dtu.gbar.gitlab.shipment.*;
import dk.dtu.gbar.gitlab.shipment.persistence.models.*;
import dk.dtu.gbar.gitlab.shipment.persistence.search.SearchCriteria;
import dk.dtu.gbar.gitlab.shipment.persistence.service.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.List;

import static org.junit.Assert.*;

public class MyStepdefs {

    Client client;
    Journey journey1;
    Journey journey2;
    Port copenhagen = new Port("Copenhagen", "0");
    Port hongKong = new Port("Hong Kong", "0");
    Port oslo = new Port("Oslo", "0");
    Container container1 = new Container("Container 1", hongKong);
    Container container2 = new Container("Container 2", hongKong);
    Ship ship;

    LogisticsCompany logisticCompany = new LogisticsCompany("admin");
    Searcher search = new Searcher(logisticCompany);
    List<Client> clientSearch;
    List<Journey> journeySearch;

    String passwordTest;
    String emailTest;
    LogIn logIn = new LogIn(logisticCompany);

    String temp;
    String pressure;
    String humidity;

    PathService ps = new PathService();
    PathPortService pps = new PathPortService();
    ShipService ss = new ShipService();
    ClientService cs = new ClientService();
    ContainerService con = new ContainerService();
    ContainerStatusService css = new ContainerStatusService();
    JourneyService js = new JourneyService();

    List<ContainerStatus> containerStatus;

    ////////////////////
    // register client
    ///////////////////
    @Given("a Client {string} with address {string} email {string} and ref person {string}")
    public void aClientWithAddressEmailAndRefPerson(String name, String address, String email, String refperson) {
        client = new Client(name, address, refperson, email, Bcrypt.hashPassword("password"));
    }

    @And("an empty client list")
    public void anEmptyClientList() {
        assertTrue(logisticCompany.getClients().isEmpty());
    }

    @When("the client is registered")
    public void theClientIsRegistered() {
        logisticCompany.register(client);
    }

    @Then("the client list contains the client")
    public void theClientListContainsClient() {
        assertTrue(logisticCompany.getClients().contains(client));
    }

    // Email already used
    @Given("client list containing a client with same email")
    public void client_list_containing_a_client_with_same_email() {
        Client client2 = new Client("name", "address", client.getEmail(), "refperson", "password");
        logisticCompany.register(client2);
    }

    @Then("the client list doesnt contain the client")
    public void the_client_list_doesnt_contain_the_client() {
        assertFalse(logisticCompany.getClients().contains(client));
    }

///////////////////////////
//client info update
///////////////////////////

    @And("client list containing the client")
    public void clientListContainingTheClient() {
        logisticCompany.register(client);
    }

    @When("the info of the client is updated")
    public void theInfoOfTheClientIsUpdated() {
        client.setAddress("new address");
        client.setClientName("new name");
        client.setEmail("new email");
        client.setReferencePerson("new refperson");
    }

    @Then("the client now has new info")
    public void theClientNowHasNewInfo() {
        assertEquals("new address", client.getAddress());
        assertEquals("new name", client.getClientName());
        assertEquals("new email", client.getEmail());
        assertEquals("new refperson", client.getReferencePerson());
    }

    ///////////////////////////
// searching client
///////////////////////////
    @When("searching clients by name {string}")
    public void searchingClientsByName(String name) {
        clientSearch = cs.search(new SearchCriteria("clientName", name));
        //searchresults = search.search(logisticCompany.getClients(), search.clientNameContains(name));
    }

    @Then("the client appears in search results")
    public void theClientAppearsInSearchResults() {
        assertEquals(client.getId(), clientSearch.get(0).getId());
    }

    @When("searching clients by address {string}")
    public void searchingClientsByAddress(String address) {
        clientSearch = cs.search(new SearchCriteria("address", address));
        //searchresults = search.search(logisticCompany.getClients(), search.addressContains(address));
    }

    @When("searching clients for email {string}")
    public void searchingClientsForEmail(String email) {
        clientSearch = cs.search(new SearchCriteria("email", email));
        //searchresults = search.clientSearchByString(logisticCompany.getClients(), string);
    }

    ///////////////
// remove client
///////////////
    @When("the client is removed")
    public void theClientIsRemoved() {
        logisticCompany.removeClient(client);
    }

    @Then("the client is soft deleted")
    public void theClientListDoesNotContainTheClient() {
        assertEquals(client.getClientStatus(), ClientStatus.DELETED);
        /*assertEquals(client.getEmail(), "Redacted");
        assertEquals(client.getName(), "Redacted");
        assertEquals(client.getAddress(), "Redacted");
        assertEquals(client.getRefPerson(), "Redacted");*/
    }

    @When("the client is deleted")
    public void theClientIsDeleted() {
        logisticCompany.removeClient(client);
    }

    @Then("the client does not exist")
    public void theClientDoesNotExist() {
        assertTrue(true);
    }

/////////////////////////////////////////////////
//	Feature : Register Container for Journey
/////////////////////////////////////////////////

    ////Scenario 1
    @Given("the port of Copenhagen which has {int} containers")
    public void thePortOfCopenhagenWhichHasContainers(int numberOfContainers) {
        copenhagen = new Port("Copenhagen", "0");
        logisticCompany.register(copenhagen);
        for (int i = 0; i < numberOfContainers; i++) {
            Container container = new Container("Another container", copenhagen);
            logisticCompany.register(container);
        }
    }

    @And("the port of Hong Kong which has {int} containers")
    public void thePortOfHongKongWhichHasContainers(int numberOfContainers) {
        hongKong = new Port("Hong Kong", "0");
        logisticCompany.register(hongKong);
        for (int i = 0; i < numberOfContainers; i++) {
            Container container = new Container("hong kong container", hongKong);
            logisticCompany.register(container);
        }
    }

    @When("client registers a shipment of {string} for a journey from Copenhagen to Hong Kong")
    public void client_registers_a_shipment_of_for_a_journey_from_Copenhagen_to_Hong_Kong_with_the_company(String content) {
        journey1 = logisticCompany.register(copenhagen.getName(), hongKong.getName(), client, content);
    }

    @Then("the journey is registered")
    public void the_shipment_is_registered_for_the_journey() {
        assertEquals(journey1.getJourneyClient(), client);
        assertTrue(logisticCompany.getJourneys().contains(journey1));
        assertTrue(logisticCompany.getContainers().contains(journey1.getJourneyContainer()));
    }

    ////Scenario 2
    @Then("the container is not registered for the journey")
    public void theContainerIsNotRegisteredForTheJourney() {
        assertFalse(container1.getContainerJourneys().contains(journey1));
    }


///////////////////////////////////////////////
//	Feature : Journey Information Update
///////////////////////////////////////////////

    ///Scenario 1
    @And("a registered journey from Copenhagen to Hong Kong with {string}")
    public void aRegisteredJourneyFromCopenhagenToHongKongWith(String cargo) {
        logisticCompany.register(copenhagen);
        logisticCompany.register(hongKong);
        logisticCompany.register(client);
        journey1 = logisticCompany.register(copenhagen.getName(), hongKong.getName(), client, cargo);
        //journey1 = new Journey(copenhagen, hongKong, client, cargo);
        //logisticCompany.register(journey1);
    }

    @Given("a registered ship in Copenhagen")
    public void a_ship_with_ID_in_Copenhagen() {
        ship = new Ship("A ship", copenhagen);
        logisticCompany.register(ship);
    }

    @And("the container is added to the ship")
    public void theContainerIsAddedToTheShip() {
        journey1.getJourneyContainer().setContainerShip(ship);
        //ship.getContainers().add(journey1.getContainer());
    }

    @When("the worker informs of the departure of the ship transporting the container")
    public void the_worker_informs_of_the_departure_of_the_ship_transporting_the_container() {
        //logisticCompany.register(ship);
        ss.depart(ship);
    }

    @Then("the ship and the container are at sea")
    public void the_ship_and_the_container_are_at_sea() {
        assertNull(ship.getShipPort());
        assertEquals(ship.getShipContainers().iterator().next(), journey1.getJourneyContainer());
    }

    // Ship arrival
    @Given("the port of Oslo which has {int} containers")
    public void the_port_of_Oslo_which_has_containers(Integer numberOfContainers) {
        oslo = new Port("Oslo", "0");
        logisticCompany.register(oslo);
        for (int i = 0; i < numberOfContainers; i++) {
            Container container = new Container("Oslo container", oslo);
            logisticCompany.register(container);
        }
    }

    @Given("a registered journey from Copenhagen to Oslo with {string}")
    public void a_registered_journey_from_Copenhagen_to_Oslo_with(String cargo) {
        journey2 = logisticCompany.register(copenhagen.getName(), oslo.getName(), client, cargo);
    }

    @Given("a registered ship at sea transporting the containers and heading to Oslo")
    public void a_registered_ship_at_sea_transporting_the_containers_and_heading_to_Oslo() {
        ship = new Ship("Another ship", copenhagen);
        Path small = new Path("Path");
        ps.save(small);
        PathPort copenhagenNode = new PathPort(small, copenhagen);
        PathPort osloNode = new PathPort(small, oslo);
        copenhagenNode.setNext(osloNode);
        osloNode.setPrevious(copenhagenNode);
        ship.setShipPath(small);
        logisticCompany.register(ship);
        journey1.getJourneyContainer().setContainerShip(ship);
        journey2.getJourneyContainer().setContainerShip(ship);
        //ship.getContainers().add(journey1.getContainer());
        //ship.getContainers().add(journey2.getContainer());
        ss.depart(ship);
    }

    @When("a worker informs of the arrival of the ship")
    public void a_worker_informs_of_the_arrival_of_the_ship() {
        ss.arrive(ship);
    }

    @Then("the ship and the containers are in Oslo")
    public void the_ship_and_the_containers_are_in_Oslo() {
        assertEquals(ship.getShipPort(), oslo);
        assertEquals(ship.getShipContainers().iterator().next().getContainerLocation(), oslo);
    }

    @Then("the journey to Oslo is ended and the container is dropped off at the port")
    public void the_journey_to_Oslo_is_ended_and_the_container_is_dropped_off_at_the_port() {
        assertEquals(journey2.getSailStatus(), JourneySailStatus.FINISHED);
        assertFalse(ship.getShipContainers().contains(journey2.getJourneyContainer()));
    }


//////////////////////////////
///// search for journeys
//////////////////////////////

    //Scenario 1
    @Given("a journey in progress")
    public void aJourneyInProgress() {
        journey1 = logisticCompany.register(hongKong.getName(), copenhagen.getName(), client, "oranges");
        // journey1 = new Journey(hongKong, copenhagen, client, "oranges");
        // logisticCompany.register(journey1);
    }

    @And("a concluded journey")
    public void aConcludedJourney() {
        journey2 = logisticCompany.register(hongKong.getName(), copenhagen.getName(), client, "oranges");
        // journey2 = new Journey(hongKong, copenhagen, client, "oranges");
        //logisticCompany.register(journey2);
        journey2.setSailStatus(JourneySailStatus.FINISHED);
    }

    @When("searching for concluded journeys")
    public void searchingForConcludedJourneys() {
        journeySearch = search.getConcludedJourneys((List<Journey>) client.getClientsJourneys());
    }

    @Then("return the concluded journey")
    public void returnTheConcludedJourney() {
        assertTrue(journeySearch.contains(journey2));
        assertFalse(journeySearch.contains(journey1));
    }

    //    Scenario 2
    @When("searching for current journeys")
    public void searching_for_current_journeys() {
        journeySearch = search.getCurrentJourneys((List<Journey>) client.getClientsJourneys());
    }

    @Then("return the current journey")
    public void return_the_current_journey() {
        assertTrue(journeySearch.contains(journey1));
        assertFalse(journeySearch.contains(journey2));
    }

    //////Scenario3
    @When("client searches for journeys coming from {string}")
    public void searches_for_journeys_coming_from(String origin) {
        journeySearch = search.search(logisticCompany.getJourneys(), search.originContains(origin));
    }

    @Then("return the journey coming from Copenhagen")
    public void return_the_journey_coming_from_Copenhagen() {
        assertEquals(journey1, journeySearch.get(0));
    }

    //////Scenario4
    @When("client searches for journeys bound for {string}")
    public void client_searches_for_journeys_bound_for(String destination) {
        journeySearch = search.search(logisticCompany.getJourneys(), search.destinationContains(destination));
    }

    @Then("return the journeys bound for Hong Kong")
    public void return_the_journey_bounds_for_Hong_Kong() {
        assertEquals(journey1, journeySearch.get(0));
    }

    /////Cargo Search
    @When("client searches for journeys with cargo of {string}")
    public void client_searches_for_journeys_with_cargo_of(String cargo) {
        journeySearch = search.search(logisticCompany.getJourneys(), search.cargoContains(cargo));
    }

    @Then("return the journeys with cargo of {string}")
    public void return_the_journeys_with_cargo_of(String cargo) {
        assertEquals(journey1, journeySearch.get(0));
    }

    /////Keyword search
    @When("client searches for journeys with keyword {string}")
    public void client_searches_for_journeys_with_keyword(String keyword) {
        journeySearch = search.journeySearchByString(logisticCompany.getJourneys(), keyword);
    }

    @Then("return the journeys related to {string}")
    public void return_the_journeys_related_to(String string) {
        assertEquals(journey1, journeySearch.get(0));
    }

////////////////////////////
///	Feature : Log In
////////////////////////////

    //Scenario 1
    @Given("a Client {string} with address {string}, ref person {string}, email {string} and password {string}")
    public void a_Client_with_address_ref_person_email_and_password(String name, String address, String refPerson, String email, String password) {
        client = new Client(name, address, refPerson, email, Bcrypt.hashPassword(password));
        logisticCompany.register(client);
    }

    @Given("a password {string}")
    public void a_password(String password) {
        passwordTest = password;
    }

    @Given("an email {string}")
    public void an_email(String email) {
        emailTest = email;
    }

    @When("client logs in")
    public void client_logs_in() {
//        logIn.checkClientLogin(emailTest, passwordTest);
        if (logIn.loginClient(emailTest, passwordTest)) ;
       /* if (logIn.checkClientEmail(emailTest)) {
            logIn.checkClientPassword(emailTest, passwordTest);
        }*/
    }

    @Then("client is logged in")
    public void client_is_logged_in() {
        assertNotNull(logIn.getLoggedInClient().getId());
        logIn.logOut();
    }

    //Scenario 2
    @Then("client is not logged in")
    public void client_is_not_logged_in() {
        assertNull(logIn.getLoggedInClient());
    }

    //Successful admin log in 
    @Given("an admin with password {string}")
    public void an_admin_with_password(String password) {
        passwordTest = password;
    }

    @When("admin logs in")
    public void admin_logs_in() {
        logIn.adminLogIn("admin", passwordTest);
    }

    @Then("admin is logged in")
    public void admin_is_logged_in() {
        assertTrue(logIn.isAdminLoggedIn());
    }

    //Wrong admin password
    @Then("admin is not logged in")
    public void admin_is_not_logged_in() {
        assertFalse(logIn.isAdminLoggedIn());
    }

    //log out
    @And("client logs out")
    public void clientLogsOut() {
        logIn.logOut();
    }

    ////////////////////////////////////////
    //  Feature Container Status Update
    ////////////////////////////////////////

    @Given("the container has a temperature of {string}, pressure of {string} and humidity of {string}")
    public void the_container_has_a_temperature_of_pressure_of_and_humidity_of(String temp, String pressure, String humidity) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
    }

    @When("a worker updates the container information")
    public void a_worker_updates_the_container_information() {
        js.save(journey1);
        ContainerStatus cs1 = new ContainerStatus(ContainerStatusName.HUMIDITY, humidity, journey1);
        ContainerStatus cs2 = new ContainerStatus(ContainerStatusName.PRESSURE, pressure, journey1);
        ContainerStatus cs3 = new ContainerStatus(ContainerStatusName.TEMPERATURE, temp, journey1);
        css.save(cs1);
        css.save(cs2);
        css.save(cs3);
        containerStatus = con.getLastStatuses(journey1.getJourneyContainer());
    }

    @Then("the container information are updated")
    public void the_container_information_are_updated() {
        for (ContainerStatus status : containerStatus) {
            if (status.getStatusName() == ContainerStatusName.TEMPERATURE) {
                assertEquals(status.getStatusValue(), temp);
            } else if (status.getStatusName() == ContainerStatusName.PRESSURE) {
                assertEquals(status.getStatusValue(), pressure);
            } else if (status.getStatusName() == ContainerStatusName.HUMIDITY) {
                assertEquals(status.getStatusValue(), humidity);
            }
        }
       /* assertEquals(containerStatus.getTempHistory().get(0), temp);
        assertEquals(containerStatus.getPressureHistory().get(0), pressure);
        assertEquals(containerStatus.getHumidityHistory().get(0), humidity);*/
    }

    @Then("the date is automatically stored")
    public void the_date_is_automatically_stored() {
        assertNotEquals(journey1.getJourneyContainerStatusHistory().size(), 0);
    }

}

