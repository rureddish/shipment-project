package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class EntityList<V extends Entity> {
    protected ArrayList<V> list;
    protected Integer idNumber = 0;

    public EntityList() {
        list = new ArrayList<>();
    }

    // add entities and assign id
    public void add(V object) {
        if (list.contains(object)){
            System.out.println("already registered");
        }
        else if (object instanceof Journey && journeyOriginHasNoContainers((Journey) object)){
            System.out.println("no containers in port");
        }
        else {
            idNumber++;
            object.setID(idNumber);
            list.add(object);
            if (object instanceof Journey){
                ((Journey) object).setContainer(((Journey) object).getOrigin().getLocationContainers().remove());
            }
        }
    }

    private boolean journeyOriginHasNoContainers(Journey object) {
        return object.getOrigin().getLocationContainers().isEmpty();
    }

    public void remove(V entity) {
        list.remove(entity);
    }

    public ArrayList<V> getList() {
        return list;
    }
}

