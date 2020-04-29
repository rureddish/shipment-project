package dk.dtu.gbar.gitlab.shipment;

/**
 * Database containing all entities in the system.
 * Contains list of clients,
 */
public class LogisticsCompany extends Client{
	private ClientList clientList = new ClientList();
	private EntityList<Container> containerList = new EntityList<>();
	private JourneyList journeyList = new JourneyList();
	private EntityList<Ship> shipList = new EntityList<>();

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
	
	//Getters and Setters


	public EntityList<Ship> getShipList() {
		return shipList;
	}

	public ClientList getClientList() {
		return clientList;
	}

	public EntityList<Container> getContainerList(){
		return containerList;
	}
	
	public EntityList<Journey> getJourneyList(){
		return journeyList;
	}
}
