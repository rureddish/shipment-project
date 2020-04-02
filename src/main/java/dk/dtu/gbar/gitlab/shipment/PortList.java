package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.HashMap;

public class PortList extends List<Port> {
    public PortList() {
        super();
    }

    public ArrayList<Port> searchByString (String criterium){
        ArrayList<Port> results = new ArrayList<>();
        for (Port Port: list.values()){
            if (Port.getPlaceName().equals(criterium)){
                results.add(Port);
            }
        }
        return results;
    }
}
