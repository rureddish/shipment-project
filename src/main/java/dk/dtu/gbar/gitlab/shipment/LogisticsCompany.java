package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.List;

/**
 * Database containing all entities in the system.
 * Contains list of clients,
 */
public class LogisticsCompany extends Client{
	private ArrayList<Client> clientList = new ArrayList();
	private ArrayList<Container> containerList = new ArrayList<>();
	private ArrayList<Journey> journeyList = new ArrayList<>();
	private ArrayList<Ship> shipList = new ArrayList<>();
	private ArrayList<Location> locationList = new ArrayList<>();
	Searcher search = new Searcher<>();

	/**
	 *
	 * @param name The name of the logistics company using the software
	 * @param address The mailing address
	 * @param refPerson
	 * @param email
	 * @param password
	 */
	public LogisticsCompany(String name, String address, String refPerson, String email, String password) {
		super(name, address, refPerson, email, password);
	}

	public void register(Client client) {
		if (!clientEmailAlreadyInUse(client)) {
			client.setID(clientList.size());
			clientList.add(client);
		}
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
