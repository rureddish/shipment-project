package dk.dtu.gbar.gitlab.shipment;

import java.util.*;

public class Location extends Entity {

    private String placeName;

    private Queue<Container> portcontainers = new LinkedList<Container>();

    public Location(String placeName, int containers, EntityList<Container> containerList){
        this.placeName = placeName;
        for(int i = 0; i < containers; i++){
            Container container = new Container(this);
            containerList.add(container);
        }
    }

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
