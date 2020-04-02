package dk.dtu.gbar.gitlab.shipment;

import java.util.*;

public class Port extends Entity {
    private String placeName;
    private Queue<Container> portcontainers = new LinkedList<Container>();

    public Port(String placeName){
        this.placeName = placeName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Queue<Container> getPortcontainers() {
        return portcontainers;
    }

    public void setPortcontainers(Queue<Container> portcontainers) {
        this.portcontainers = portcontainers;
    }
}
