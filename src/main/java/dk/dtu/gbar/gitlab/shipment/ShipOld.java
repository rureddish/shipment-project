package dk.dtu.gbar.gitlab.shipment;

import org.hsqldb.persist.Log;

import java.util.*;

/**
 * Holds containers and is used to process journeys.
 */
public class ShipOld{
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
	public ShipOld (Location location, LogisticsCompany logisticsCompany) {
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
		for (int i = 0; i < containers.size(); i++) {
			logisticsCompany.atSea.getLocationContainers().remove(containers.get(i));
			containers.get(i).setLocation(location);
			if (containers.get(i).getJourneyHistory().lastElement().getDestination()==location) {
				containers.get(i).getJourneyHistory().lastElement().endJourney();
				location.getLocationContainers().add(containers.get(i));
				containers.remove(containers.get(i));
			}
		}
	}

	public Location getLocation() {
		return location;
	}

	public ArrayList<Container> getContainers() {
		return containers;
	}
	
	public Queue<Location> getRoute(){
		return route;
	}
}
