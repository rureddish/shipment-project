package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;

public class LocationList extends EntityList<Location> {
    public LocationList() {
        super();
    }

    public ArrayList<Location> searchByString (String criterium){
        ArrayList<Location> results = new ArrayList<>();
        for (Location Location : list.values()){
            if (Location.getPlaceName().equals(criterium)){
                results.add(Location);
            }
        }
        return results;
    }
}
