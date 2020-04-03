package dk.dtu.gbar.gitlab.shipment;

import java.util.*;

public class Location extends Entity {
    private String placeName;
    private Queue<Container> portcontainers = new LinkedList<Container>();

    public Location(String placeName){
        this.placeName = placeName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public Queue<Container> getPortcontainers() {
        return portcontainers;
    }

}
