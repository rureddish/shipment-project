package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.List;

/**
 * Database containing all entities in the system.
 * Contains list of clients,
 */
public class LogisticsCompany{
	private String password;
	private ArrayList<Client> clientList = new ArrayList();
	private ArrayList<Container> containerList = new ArrayList<>();
	private ArrayList<Journey> journeyList = new ArrayList<>();
	private ArrayList<Ship> shipList = new ArrayList<>();
	private ArrayList<Location> locationList = new ArrayList<>();
	Searcher search = new Searcher<>();

	/**
	 *
	 * @param password
	 */
	public LogisticsCompany(String password) {
		this.password = password;
	}

	public boolean register(Client client) {
		if (!clientEmailAlreadyInUse(client)) {
			client.setID(clientList.size());
			clientList.add(client);
			return  true;
		}
		return false;
	}

	public void register(Container container){
		container.setID(containerList.size());
		containerList.add(container);
	}

	public void register(Location location){
		location.setID(locationList.size());
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
		ship.setID(shipList.size());
		shipList.add(ship);
	}

	public void removeClient(Client client) {
		client = clientList.get(client.getID());
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
}
