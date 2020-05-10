package dk.dtu.gbar.gitlab.shipment.old;

/**
 * A shipment belonging to a client.
 *
 */
public class JourneyOld {
    private Container container;
    private Client client;
	private Location origin;
	private Location destination;
    private boolean isConcluded;
    private String cargo;
    private Integer ID;

    /**
     * @param origin The port of departure.
     * @param destination Destination of the journey.
     * @param client The client that registered the journey.
     * @param cargo The content of the container
     */
	public JourneyOld(Location origin, Location destination, Client client, String cargo) {
        this.origin = origin;
        this.destination = destination;
        this.client = client;
        this.cargo = cargo;
        this.isConcluded = false;
    }

    /**
     *
     */
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

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
}