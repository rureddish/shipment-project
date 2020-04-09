package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class JourneyList extends EntityList<Journey> {
    public JourneyList() {
        super();
    }

    @Override
    // only add if port of origin has containers
    public void add(Journey journey) {
        if (!journey.getOrigin().getLocationContainers().isEmpty()) {
            idNumber++;
            journey.setID(idNumber);
            journey.setContainer(journey.getOrigin().getLocationContainers().remove());
            list.add(journey);
        } else {
            System.out.println("Journey could not be registered - no containers in port");
        }
    }
}

