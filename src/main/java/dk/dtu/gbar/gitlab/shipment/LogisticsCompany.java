package dk.dtu.gbar.gitlab.shipment;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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

	PropertyChangeSupport support = new PropertyChangeSupport(this);

	/**
	 *
	 * @param password Password for logging in as the logistics company.
	 */
	public LogisticsCompany(String password) {
		this.password = password;
	}

	/**
	 * Registers client if email not in use, returns false otherwise
	 * @param client
	 * @return
	 */
	public boolean register(Client client) {
		if (!clientEmailAlreadyInUse(client)) {
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
		locationList.add(location);
	}

	/** if there are containers at origin journey is registerstered.
	 * returns false if not successful
	 * @param journey Journey being registered
	 * @return
	 */
	public boolean register(Journey journey) {
		if (journeyOriginHasContainers(journey)) {
			journey.setID(journeyList.size());
			journeyList.add(journey);
			journey.setContainer(journey.getOrigin().getLocationContainers().remove());
			journey.getContainer().getJourneyHistory().add(journey);
			journey.getClient().getJourneys().add(journey);

			support.firePropertyChange("Journey Added",null,null);
			return true;
		} else {
			return  false;
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

	public void addObserver(PropertyChangeListener listener) {
		support.addPropertyChangeListener(listener);
	}
}