package dk.dtu.gbar.gitlab.shipment.old;

import java.util.*;

/**
 * Objects have locations which change with journeys.
 */
public class LocationOld {
    private String placeName;
    private Queue<Container> portcontainers = new LinkedList<>();

    /**
     * @param placeName The name of the location to be registered
     */
    public LocationOld(String placeName){
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
