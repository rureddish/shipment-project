package dk.dtu.gbar.gitlab.shipment;


import java.util.ArrayList;
import java.util.Stack;

public class Container extends Entity {
    private Stack<Journey> journeyHistory = new Stack<>();
    private Location location;

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
}

