package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.Arrays;

public class Ship {
	private ArrayList<Container> containers;
	private int ID;
	private Location location;
	
///Constructor
	public Ship(int ID, Location location) {
		this.ID = ID;
		this.location = location;
	}

///Methods
    public void embark(Searcher<Container> searcher, EntityList<Container> containerList, int... containerIDs){
    	containers = searcher.search(containerList.getList(), searcher.withID(containerIDs));
    	location = new Location("At sea");
    	for (Container container : containers) {
    		container.setLocation(new Location("At sea"));
    	}
	}
 
	public void arrive(Location location){
		for (Container container : this.containers) {
			container.setLocation(location);
			container.getCurrentJourney().endJourney();
		}
		
	}

	public Location getLocation() {
		return location;
	}

	public ArrayList<Container> getContainers() {
		return containers;
	}
}
