package dk.dtu.gbar.gitlab.shipment;


import java.util.ArrayDeque;
import java.util.Deque;

public class Container extends Entity {
    private  Deque<Port> locationHistory;		//LIFO to get the last element 
    private  Deque<Journey> journeyHistory;		//use peek() to get the last element
    private ContainerStatus status;
    private boolean isUsed;
    private String content;


	public Container(Port location) {
        locationHistory = new ArrayDeque<>();
        journeyHistory = new ArrayDeque<>();
        locationHistory.push(location);      		//push at the top of the list 		
        location.getPortcontainers().add(this);		//add container to port
    }

	
	public void newJourney(Journey journey) {
		journeyHistory.add(journey);
	}
	
    // getters and setters

	public Deque<Journey> getJourneyHistory() {
		return journeyHistory;
	}

//	public void setJourneyHistory(Deque<Journey> journeyHistory) {
//		this.journeyHistory = journeyHistory;
//	}

	public Deque<Port> getLocationHistory() {
		return locationHistory;
	}

//	public void setLocationHistory(Deque<Port> locationHistory) {
//		this.locationHistory = locationHistory;
//	}
	
    public Deque<Port> getLocation() {
        return locationHistory;
    }

    public boolean isUsed() {
		return isUsed;
	}

	public void setUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}

