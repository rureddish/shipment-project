package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.HashMap;

public class ContainerList extends List<Container> {
    public ContainerList() {
        super();
        list = new HashMap<>();
    }

    public ArrayList<Container> searchByLocation (String criterium){
        ArrayList<Container> results = new ArrayList<>();
        for (Container l: list.values()){
            if (l.getLocation().equals(criterium)){
                results.add(l);
            }
        }
        return results;
    }

    public HashMap<Integer, Container> getList() {
        return list;
    }

}
