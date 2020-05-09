package dk.dtu.gbar.gitlab.shipment;


import java.util.ArrayList;
import java.util.Stack;

/**
 * Contains cargo and is moved to process the journey.
 *
 */
public class Container {
    private Stack<Journey> journeyHistory = new Stack<>();
    private Location location;
    private Integer ID;

    /**
     * @param location The location at which the container is registered.
     */
	public Container(Location location) {
        this.location=location;
        location.getLocationContainers().add(this);
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
}

