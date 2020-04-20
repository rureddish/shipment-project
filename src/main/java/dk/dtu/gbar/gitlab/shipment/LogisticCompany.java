package dk.dtu.gbar.gitlab.shipment;

public class LogisticCompany extends Client{
	private EntityList<Client> clientList;
	private EntityList<Container> containerList;
	private EntityList<Journey> journeyList;
	private Searcher<Client> searchClient;
	private Searcher<Container> searchContainer;
	private Searcher<Journey> searchJourney;
	
	public LogisticCompany(String name, String address, String refPerson, String email) {
		super(name, address, refPerson, email);
		clientList = new EntityList<>();
		containerList = new EntityList<>();
		journeyList = new EntityList<>();
		searchClient = new Searcher<>();
		searchContainer = new Searcher<>();
		searchJourney = new Searcher<>();
	}
	
	//Getters and Setters
	public EntityList<Container> getContainerList(){
		return containerList;
	}
	
	public EntityList<Journey> getJourneyList(){
		return journeyList;
	}
	
	public Searcher<Container> getSearchContainer(){
		return searchContainer;
	}

}
