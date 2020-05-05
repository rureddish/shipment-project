package dk.dtu.gbar.gitlab.shipment;

import java.util.*;

/**
 * Holds containers and is used to process journeys.
 */
public class Ship{
	private ArrayList<Container> containers = new ArrayList<>();
	private Location location;
	Location atSea = new Location("At sea");
	private Queue<Location> route = new LinkedList<>();

///Constructor

	/**
	 *
	 * @param location
	 */
	public Ship (Location location) {
		this.location = location;
	}

///Methods
    public void depart(){
    	location = atSea;
    	for (Container container : containers) {
    		container.setLocation(atSea);
    	}
	}
 
	public void arrive(){
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
