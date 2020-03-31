package dk.dtu.gbar.gitlab.shipment;

public class Journey {
	private String portOfOrigin;
	private String portOfDestination;
	private String company;
	private String journeyID;
	private Container container;
	
//	public Journey() {
//		super();
//	}

	public Journey(String portOfOrigin, String portOfDestination, String company, Container container) {
		super();
		this.portOfOrigin = portOfOrigin;
		this.portOfDestination = portOfDestination;
		this.company = company;
		this.container = container;
		this.journeyID = this.company + this.container;
	}
	
	public String getJourneyID() {
		return journeyID;
	}

//	private void setJourneyID(String journeyID) {
//		this.journeyID = journeyID;
//	}

	public Container getContainer() {
		return container;
	}

	public void setContainer(Container container) {
		this.container = container;
	}
		
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	public String getPortOfOrigin() {
		return portOfOrigin;
	}
	public void setPortOfOrigin(String portOfOrigin) {
		this.portOfOrigin = portOfOrigin;
	}
	public String getPortOfDestination() {
		return portOfDestination;
	}
	public void setPortOfDestination(String destination) {
		this.portOfDestination = destination;
	}
	
	
}
