package dk.dtu.gbar.gitlab.shipment;

import org.hsqldb.persist.Log;

import java.util.*;

public class Location extends Entity {

    private String placeName;
    private Queue<Container> portcontainers = new LinkedList<Container>();
    private LogisticsCompany logisticsCompany;


    public Location(String placeName, int containers, LogisticsCompany logisticsCompany){
        this.placeName = placeName;
        this.logisticsCompany = logisticsCompany;
        for(int i = 0; i < containers; i++){
            Container container = new Container(this);
            this.logisticsCompany.getContainerList().add(container);
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
