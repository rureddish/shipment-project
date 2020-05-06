package dk.dtu.gbar.gitlab.shipment;

import org.hsqldb.persist.Log;

import java.util.*;

/**
 * Holds containers and is used to process journeys.
 */
public class Ship{
	private ArrayList<Container> containers = new ArrayList<>();
	private Location location;
	Location atSea = new Location("At sea");
	private Queue<Location> route = new LinkedList<>();
	private LogisticsCompany logisticsCompany;

///Constructor

	/**
	 *
	 * @param location
	 */
	public Ship (Location location, LogisticsCompany logisticsCompany) {
		this.location = location;
		this.logisticsCompany = logisticsCompany;
	}

///Methods
    public void depart(){
    	location = logisticsCompany.atSea;
    	for (Container container : containers) {
    		container.setLocation(location);
    		location.getLocationContainers().add(container);
    	}
	}
 
	public void arrive(){
		location = route.remove();
		for (Container container : containers) {
			logisticsCompany.atSea.getLocationContainers().remove();
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
