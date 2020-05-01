package dk.dtu.gbar.gitlab.shipment;

public class Journey extends Entity {
    private Container container;
    private Client client;
	private ContainerStatus status;
	private Location origin;
	private Location destination;
    private boolean isConcluded;
    private String cargo;

	public Journey(Location origin, Location destination, Client client, String cargo) {
        this.origin = origin;
        this.destination = destination;
        this.client = client;
        this.cargo = cargo;
        this.isConcluded = false;
        client.getJourneys().add(this);
    }

	public void endJourney() {
		isConcluded = true;
	}

	public boolean isConcluded() {
        return isConcluded;
    }

    // getters and setters
	public String getCargo() {return cargo; }

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Client getClient() {
        return client;
    }

    public Location getOrigin() {
        return origin;
    }

    public Location getDestination() {
        return destination;
    }
}
