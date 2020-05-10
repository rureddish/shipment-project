package dk.dtu.gbar.gitlab.shipment.old;


import dk.dtu.gbar.gitlab.shipment.ContainerStatus;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Contains cargo and is moved to process the journey.
 *
 */
public class ContainerOld {
    private Stack<Journey> journeyHistory = new Stack<>();
    private Location location;
    private Integer ID;
    private ContainerStatus statusHistory;

    /**
     * @param location The location at which the container is registered.
     */
	public ContainerOld(Location location) {
        this.location=location;
        location.getLocationContainers().add(this);
        statusHistory = new ContainerStatus();
    }

	public Location getLocation() {
		return location;
	}

    public void setLocation(Location location) {
        this.location = location;
    }

    public Stack<Journey> getJourneyHistory() {
        return journeyHistory;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }
    
    public ContainerStatus getStatusHistory(){
    	return statusHistory;
    }
}

