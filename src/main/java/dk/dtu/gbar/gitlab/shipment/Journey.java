package dk.dtu.gbar.gitlab.shipment;

public class Journey extends Entity {
    private Container container;
    private Client client;
	private Location origin;
	private Location destination;
	private boolean isConcluded;
	private String cargo;

	public Journey(Location origin, Location destination, Client client, String cargo) throws ErrorException {
		if (origin.hasContainers()) {
			this.origin = origin;
	        this.destination = destination;
	        this.client = client;
	        container = origin.getLocationContainers().remove();
	        this.cargo = cargo;
        } else {
        	throw new ErrorException("No container available in the port of origin");
        }
		
    }
	 
	
	public void endJourney() {
		isConcluded = true;
		container.getJourneyHistory().add(this);
	}
	

    // getters and setters

	public boolean isConcluded() {
		return isConcluded();
//      return container.getLocation() == destination;
    }

	public String getCargo() {return cargo; }

    public Container getContainer() {
        return container;
    }

//    public void setContainer(Container container) {
//        this.container = container;
//    }

    public Client getClient() {
        return client;
    }

    public Location getOrigin() {
        return origin;
    }

    public Location getDestination() {
        return destination;
    }
    
    public void setConcluded() {
    	isConcluded = true;
    }
}
