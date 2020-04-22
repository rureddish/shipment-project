package dk.dtu.gbar.gitlab.shipment;

public class LogisticsCompany extends Client{
	private ClientList clientList = new ClientList();
	private EntityList<Container> containerList = new EntityList<>();
	private JourneyList journeyList = new JourneyList();
	private EntityList<Ship> shipList = new EntityList<>();

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
