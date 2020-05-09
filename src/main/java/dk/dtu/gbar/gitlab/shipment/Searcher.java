package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Finds objects by criteria(predicates).
 * Can be called with list of predicated to find entities matching every predicate (inclusively).
 * To find entities matching multiple criteria (exclusively) call search with each predicate.
 */
public class Searcher {
    LogisticsCompany logisticsCompany;

    public Searcher(LogisticsCompany logisticsCompany) {
        this.logisticsCompany=logisticsCompany;
    }


    public ArrayList search(ArrayList list, Predicate predicate){
        return (ArrayList) list.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    public ArrayList search(ArrayList list, List<Predicate> predicates){
        ArrayList searchResult = (ArrayList) list.stream()
                .filter(predicates.stream().reduce(x -> false, Predicate::or))
                .collect(Collectors.toList());
        return searchResult;
    }

    //Client

    public ArrayList<Journey> getCurrentJourneys(ArrayList<Journey> journeys){
        return search(journeys, excludeConcludedJourneys);
    }

    public ArrayList<Journey> getConcludedJourneys(ArrayList<Journey> journeys){
        return search(journeys, excludeCurrentJourneys );
    }


    public ArrayList clientSearchByString(ArrayList<Client> list, String string) {
        List allClientStringFieldPredicates = Arrays.asList(clientNameContains(string), emailContains(string), refPersonContains(string), addressContains(string));
        return search(list, allClientStringFieldPredicates);
    }

    public Predicate<Client> clientNameContains(String string) {
        return (str -> str.getName().toLowerCase().contains(string.toLowerCase()));
    }

    public Predicate<Client> emailContains(String string) {
        return (str -> str.getEmail().toLowerCase().contains(string.toLowerCase()));
    }

    public Predicate<Client> refPersonContains(String string) {
        return (str -> str.getRefPerson().toLowerCase().contains(string.toLowerCase()));
    }

    public Predicate<Client> addressContains(String string) {
        return (str -> str.getAddress().toLowerCase().contains(string.toLowerCase()));
    }

    // Journey
    public Predicate<Journey> excludeConcludedJourneys = (x -> !x.isConcluded());

    public Predicate<Journey> excludeCurrentJourneys = (Journey::isConcluded);


    public Predicate<Journey> originContains(String string) {
        return (x -> x.getOrigin().getPlaceName().toLowerCase().contains(string.toLowerCase()));
    }

    public Predicate<Journey> destinationContains(String string) {
        return (x -> x.getDestination().getPlaceName().toLowerCase().contains(string.toLowerCase()));
    }
    
    public Predicate<Journey> cargoContains(String string) {
        return (x -> x.getCargo().toLowerCase().contains(string.toLowerCase()));
    }
    
    public ArrayList journeySearchByString(ArrayList<Journey> list, String string) {
    	List allJourneyFieldPredicates = Arrays.asList(originContains(string), destinationContains(string), cargoContains(string));
    	return search(list, allJourneyFieldPredicates);
    }

    //    public Predicate<Journey> journeyLocationContains(String string) {
//        return (x -> x.getContainer().getLocation().getPlaceName().contains(string));
//    }

//      //Container
//    public ArrayList<V> containerSearchByString(ArrayList<Container> list, String string){
//        return search(list, locationContains(string));
//    }

//    public Predicate<Container> locationContains(String string) {
//        return (str -> str.getLocation().getPlaceName().contains(string));
//    }


//    --> problem
//    public Predicate<Journey> clientContains(String string) {
//        return (x -> x.getClient().getName().equalsIgnoreCase(string));
//    }
//

}
