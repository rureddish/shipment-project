package dk.dtu.gbar.gitlab.shipment;


import java.util.ArrayList;

public class Container extends Entity {
    private ArrayList<Journey> journeyHistory;
    private Location location;

	public Container(Location location) {
        journeyHistory = new ArrayList<Journey>();
        this.location=location;
        location.getLocationContainers().add(this);
    }

	public Location getLocation() {
		return location;
	}

    public void setLocation(Location location) {
        this.location = location;
    }

    public ArrayList<Journey> getJourneyHistory() {
        return journeyHistory;
    }
}

