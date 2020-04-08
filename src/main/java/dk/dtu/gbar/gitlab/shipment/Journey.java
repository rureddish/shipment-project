package dk.dtu.gbar.gitlab.shipment;

import java.util.Stack;


public class Journey extends Entity {
    private Container container;
    private Client client;
	private Stack<ContainerStatus> containerStatusHistory;	//LIFO
	private Location origin;
	private Location destination;
    private String cargoID;
    private JourneyStatus journeyStatus;

 
	public Journey(Location origin, Location destination, Client client, String content) throws ErrorException {
		if (origin.getPortContainers().isEmpty()) {
			throw new ErrorException("No container available in the port of origin");
		}
		else {
			this.origin = origin;
	        this.destination = destination;
	        this.client = client;
	        this.journeyStatus = JourneyStatus.IN_PROCESS;
	        this.container = origin.getPortContainers().remove(); 	//container is removed from the port
	        this.container.setContent(content);
	        containerStatusHistory = new Stack<>();
	        container.getJourneyHistory().add(this);
		}
    }


	public void leavingPort(Location port, String cargoID){
    	this.cargoID = cargoID;
    	Location atSea = new Location("At sea");
		container.setLocation(atSea);
		journeyStatus = JourneyStatus.IN_TRANSIT;
	}

	public void arrivalAtPort(Location port){
		container.setLocation(port);
		
		if (port.getPlaceName()==destination.getPlaceName()) {
			journeyStatus = JourneyStatus.CONCLUDED;
		}
	}
	
	public void updateContainerStatus(ContainerStatus containerStatus) {
		containerStatusHistory.add(containerStatus);
	}

	
    // getters and setters



	
    public String getCargoID() {
		return cargoID;
	}

	public Stack<ContainerStatus> getContainerStatusHistory() {
		return containerStatusHistory;
	}


//	public void setContainerStatusHistory(Stack<ContainerStatus> containerStatusHistory) {
//		this.containerStatusHistory = containerStatusHistory;
//	}


	public JourneyStatus getJourneyStatus() {
		return journeyStatus;
	}

	public void setJourneyStatus(JourneyStatus journeyStatus) {
		this.journeyStatus = journeyStatus;
	}

	public void setCargoID(String cargoID) {
		this.cargoID = cargoID;
	}

	public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Client getClient() {
        return client;
    }

//    public void setClient(Client client) {
//        this.client = client;
//    }

    public Location getOrigin() {
        return origin;
    }

    public Location getDestination() {
        return destination;
    }

}
