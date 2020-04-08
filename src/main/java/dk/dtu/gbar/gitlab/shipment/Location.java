package dk.dtu.gbar.gitlab.shipment;

import java.util.*;
 
public class Location extends Entity {
    private String placeName;
    private Queue<Container> portContainers;

    public Location(String placeName, int containerNumber, ContainerList containerList){
        this.placeName = placeName;
        portContainers = new LinkedList<Container>();
        
        for (int i=0; i<containerNumber; i++) {
        	Container container = new Container();
        	container.setLocation(this);	//doesn't work if use constructor with Location
        	containerList.add(container);   //add to the containerList --> need to be improved 
        	portContainers.add(container);
        }
    }
    
    public Location(String placeName) {
    	this.placeName = placeName;
    }
    
    
// Getters & Setters

    public String getPlaceName() {
        return placeName;
    }

    public Queue<Container> getPortContainers() {
        return portContainers;
    }

}
