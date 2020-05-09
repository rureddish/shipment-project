package dk.dtu.gbar.gitlab.shipment;

import dk.dtu.gbar.gitlab.shipment.persistence.models.Client;
import dk.dtu.gbar.gitlab.shipment.persistence.models.ClientStatus;
import dk.dtu.gbar.gitlab.shipment.persistence.search.SearchCriteria;
import dk.dtu.gbar.gitlab.shipment.persistence.service.ClientService;

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
	private ClientService cs = new ClientService();

	PropertyChangeSupport support = new PropertyChangeSupport(this);

	/**
	 *
	 * @param password Password for logging in as the logistics company.
	 */
	public LogisticsCompany(String password) {
		this.password = password;
	}


	/**
	 * 	Registers client if email not in use, returns false otherwise
	 * @param userName user name of the client
	 * @param address address
	 * @param refPerson reference person
	 * @param email email that should not be in use
	 * @param password password in plaintext
	 * @return whether the register was successful
	 */
	public boolean register(String userName,String address, String refPerson,String email, String password ) {
		if (!clientEmailAlreadyInUse(email)) {
			Client client = new Client(userName,address,refPerson,email,Bcrypt.hashPassword(password));
			cs.save(client);
			//clientList.add(client);
			return  true;
		}
		return false;
	}
	public boolean register (Client client){
		if(!clientEmailAlreadyInUse(client.getEmail())){
			cs.save(client);
			return true;
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

	/** if there are containers at origin journey is registered.
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
		/*client.setAddress("Redacted");
		client.setRefPerson("Redacted");
		client.setName("Redacted");
		client.setEmail("Redacted");*/
		client.setClientStatus(ClientStatus.DELETED);
	}

	public boolean clientEmailAlreadyInUse(String email) {
		return 0 < cs.search(new SearchCriteria("EMAIL",email)).size();
		//return 0 < search.search(clientList, search.emailContains(client.getEmail())).size();
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