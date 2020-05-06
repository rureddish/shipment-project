package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;

/**
 * Database of all objects in the system.
 * Contains list of clients, containers, journeys, locations and ships.
 * Registers objects to these lists, so they are in the system and their
 * index in the list acts as ID.
 */
public class LogisticsCompany{
	private String password;
	private ArrayList<Client> clientList = new ArrayList();
	private ArrayList<Container> containerList = new ArrayList<>();
	private ArrayList<Journey> journeyList = new ArrayList<>();
	private ArrayList<Ship> shipList = new ArrayList<>();
	private ArrayList<Location> locationList = new ArrayList<>();
	Location atSea = new Location("At sea");
	Searcher search = new Searcher(this);

	/**
	 *
	 * @param password Password for logging in as the logistics company.
	 */
	public LogisticsCompany(String password) {
		this.password = password;
	}

	public boolean register(Client client) {
		if (!clientEmailAlreadyInUse(client)) {
			clientList.add(client);
			return  true;
		}
		return false;
	}

	public void register(Container container){
		containerList.add(container);
	}

	public void register(Location location){
		locationList.add(location);
	}

	public void register(Journey journey) {
		if (journeyOriginHasContainers(journey)) {
			journeyList.add(journey);
			journey.setContainer(journey.getOrigin().getLocationContainers().remove());
			journey.getContainer().getJourneyHistory().add(journey);
		} else {
			System.out.println("no containers in port");
		}
	}

	public void register(Ship ship){
		shipList.add(ship);
	}

	public void removeClient(Client client) {
		client.setAddress("Redacted");
		client.setRefPerson("Redacted");
		client.setName("Redacted");
		client.setEmail("Redacted");
	}

	public boolean clientEmailAlreadyInUse(Client client) {
		return 0 < search.search(clientList, search.emailContains(client.getEmail())).size();
	}

	public boolean journeyOriginHasContainers(Journey object) {
		return 0 < object.getOrigin().getLocationContainers().size();
	}
	
	//Getters and Setters


	public String getPassword() {
		return password;
	}

	public ArrayList<Ship> getShipList() {
		return shipList;
	}

	public ArrayList<Client> getClientList() {
		return clientList;
	}

	public ArrayList<Container> getContainerList(){
		return containerList;
	}
	
	public ArrayList<Journey> getJourneyList(){
		return journeyList;
	}
	public ArrayList<Location> getLocationList(){
		return locationList;
	}
}
