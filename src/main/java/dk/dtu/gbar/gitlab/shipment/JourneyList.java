package dk.dtu.gbar.gitlab.shipment;

import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class JourneyList extends EntityList<Journey> {
    public JourneyList() {
        super();
        list = new HashMap<>();
    }

//    --> in Journey Class now 
    
//    @Override
//    // only add if port of origin has containers
//    public void add(Journey journey){
//        if (!journey.getOrigin().getPortcontainers().isEmpty()){
//            idNumber++;
//            journey.setID(idNumber);
//            journey.setContainer(journey.getOrigin().getPortcontainers().remove());
//            list.put(idNumber,journey);
//        } else{
//            System.out.println("no containers in port");
//        }
//    }

    // search all relevant fields by string
    public List<Journey> searchByString(String string){
        return search(originContains(string), destinationContains(string), clientContains(string), cargoContains(string));
    }
 
    // search predicates
    public Predicate<Journey> excludeConcludedJourneys = (x -> !x.getJourneyStatus().equals(JourneyStatus.CONCLUDED));

    public Predicate<Journey> excludeCurrentJourneys = (x -> x.getJourneyStatus().equals(JourneyStatus.CONCLUDED));
    
    public Predicate<Journey> currentLocationContains(String string){
        return (x -> x.getContainer().getLocation().getPlaceName().contains(string));
    }

    public Predicate<Journey> originContains(String string) {
        return (x -> x.getOrigin().getPlaceName().contains(string));
    }

    public Predicate<Journey> destinationContains (String string) {
        return (x -> x.getDestination().getPlaceName().contains(string));
    }

    public Predicate<Journey> clientContains(String string) {
        return (x -> x.getCargoID().contains(string));
    }

    public Predicate<Journey> cargoContains(String string) {
        return (x -> x.getCargoID().contains(string));
    }

}

