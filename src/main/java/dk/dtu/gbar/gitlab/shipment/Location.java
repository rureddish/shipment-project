package dk.dtu.gbar.gitlab.shipment;

import java.util.*;

public class Location extends Entity {

    private String placeName;
    private Queue<Container> portcontainers = new LinkedList<>();


    public Location(String placeName){
        this.placeName = placeName;
    }

    // getters

    public String getPlaceName() {
        return placeName;
    }

    public Queue<Container> getLocationContainers() {
        return portcontainers;
    }

}
