package dk.dtu.gbar.gitlab.shipment;

import java.util.*;

public class Ship extends Entity{
	private ArrayList<Container> containers = new ArrayList<>();
	private Location location;
	Location atSea = new Location("At sea");
	LogisticsCompany logisticsCompany;
	private Queue<Location> route = new LinkedList<>();

///Constructor
	public Ship (Location location, LogisticsCompany logisticsCompany) {
		this.location = location;
		this.logisticsCompany = logisticsCompany;
		logisticsCompany.getShipList().add(this);
	}

///Methods
    protected void embark(){
    	location = atSea;
    	for (Container container : containers) {
    		container.setLocation(atSea);
    	}
	}
 
	protected void arrive(){
		location = route.remove();
		for (Container container : containers) {
			container.setLocation(location);
			if (container.getJourneyHistory().lastElement().getDestination()==location) {
				container.getJourneyHistory().lastElement().endJourney();
				containers.remove(container);
				location.getLocationContainers().add(container);
			}
		}
	}

	public Location getLocation() {
		return location;
	}

	public ArrayList<Container> getContainers() {
		return containers;
	}
}
