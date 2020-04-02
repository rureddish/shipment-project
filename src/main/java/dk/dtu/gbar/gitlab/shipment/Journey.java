package dk.dtu.gbar.gitlab.shipment;

public class Journey extends Entity {
	private Port origin;
	private Port destination;
	private Container container;
	private Client client;
	private String cargo;

	public Journey(Port origin, Port destination, String cargo, Client client) {
		this.origin = origin;
		this.destination = destination;
		this.cargo = cargo;
		this.client = client;
	}

	//getters and setters

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCargo() {
		return cargo;
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

	public void setClient(Client client) {
		this.client = client;
	}

	public Port getOrigin() {
		return origin;
	}

	public Port getDestination() {
		return destination;
	}
}
