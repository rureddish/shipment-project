package dk.dtu.gbar.gitlab.shipment;


import java.util.ArrayList;

public class Container extends Entity {
    private ArrayList<Journey> journeyHistory = new ArrayList<Journey>();
    private Location location;
    private String content;

	public Container(Location location) {
        this.location=location;
        location.getPortcontainers().add(this);
    }
	
// Getters & Setters	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

