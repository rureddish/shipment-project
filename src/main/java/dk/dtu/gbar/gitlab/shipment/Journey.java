package dk.dtu.gbar.gitlab.shipment;

public class Journey {
	private String portOfOrigin;
	private String destination;
	private String company;
	private String journeyID;
	public String getJourneyID() {
		return journeyID;
	}

	private void setJourneyID(String journeyID) {
		this.journeyID = journeyID;
	}

	public String getContainerID() {
		return containerID;
	}

	public void setContainerID(String containerID) {
		this.containerID = containerID;
	}
	public Journey(String portOfOrigin, String destination, String company, String containerID) {
		super();
		this.portOfOrigin = portOfOrigin;
		this.destination = destination;
		this.company = company;
		this.containerID = containerID;
		
		this.setJourneyID(this.company+this.containerID);
	}
	private String containerID;
	
	
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Journey() {
		super();
	}
	
	public String getPortOfOrigin() {
		return portOfOrigin;
	}
	public void setPortOfOrigin(String portOfOrigin) {
		this.portOfOrigin = portOfOrigin;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	
}
