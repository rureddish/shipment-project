package dk.dtu.gbar.gitlab.shipment;


public class Container extends Entity {
    private Port location;
    private Journey[] journeyHistory;
    private ContainerStatus status;

    public Container(Port location) {
        this.location=location;
        location.getPortcontainers().add(this);
    }

    // getters and setters


    public Port getLocation() {
        return location;
    }


}

