package dk.dtu.gbar.gitlab.shipment;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class JourneyList extends EntityList<Journey> {
    public JourneyList() {
        super();
        list = new HashMap<>();
    }

    @Override
    // only add if port of origin has containers
    public void add(Journey journey){
        if (!journey.getOrigin().getLocationContainers().isEmpty()){
            idNumber++;
            journey.setID(idNumber);
            journey.setContainer(journey.getOrigin().getLocationContainers().remove());
            list.put(idNumber,journey);
        } else{
            System.out.println("Journey could not be registered - no containers in port");
        }
    }

    // search all relevant fields by string
    public List<Journey> searchByString(String string){
        return search(originContains(string), destinationContains(string), clientContains(string), cargoContains(string));
    }

    // search predicates
    public Predicate<Journey> excludeConcludedJourneys = (x -> !x.isConcluded());

    public Predicate<Journey> excludeCurrentJourneys = (Journey::isConcluded);
    
    public Predicate<Journey> currentLocationContains(String string){
        return (x -> x.getContainer().getLocation().getPlaceName().toLowerCase().contains(string.toLowerCase()));
    }

    public Predicate<Journey> originContains(String string) {
        return (x -> x.getOrigin().getPlaceName().toLowerCase().contains(string.toLowerCase()));
    }

    public Predicate<Journey> destinationContains (String string) {
        return (x -> x.getDestination().getPlaceName().equalsIgnoreCase(string));
    }

    public Predicate<Journey> clientContains(String string) {
        return (x -> x.getCargo().equalsIgnoreCase(string));
    }

    public Predicate<Journey> cargoContains(String string) {
        return (x -> x.getCargo().equalsIgnoreCase(string));
    }

}

