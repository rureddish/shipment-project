package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Searcher<V extends Entity> {
    // search list by predicates

    @SafeVarargs
    public final ArrayList<V> search(ArrayList list, Predicate... SearchPredicates){
        ArrayList<Predicate<V>> predicates = new ArrayList(Arrays.asList((SearchPredicates)));
        return (ArrayList<V>) list.stream()
                .filter(predicates.stream().reduce(x-> false, Predicate::or))
                .collect(Collectors.toList());
    } 

//      //Container
//    public ArrayList<V> containerSearchByString(ArrayList<Container> list, String string){
//        return search(list, locationContains(string));
//    }

//    public Predicate<Container> locationContains(String string) {
//        return (str -> str.getLocation().getPlaceName().contains(string));
//    }

    //Client
    public ArrayList<V> clientSearchByString(ArrayList<Client> list, String string) {
        return search(list, clientNameContains(string), emailContains(string), refPersonContains(string), addressContains(string));
    }

    public Predicate<Client> clientNameContains(String string) {
        return (str -> str.getName().contains(string));
    }

    public Predicate<Client> emailContains(String string) {
        return (str -> str.getEmail().contains(string));
    }

    public Predicate<Client> refPersonContains(String string) {
        return (str -> str.getRefPerson().contains(string));
    }

    public Predicate<Client> addressContains(String string) {
        return (str -> str.getAddress().contains(string));
    }

    // Journey
//    public ArrayList<V> journeySearchByString(ArrayList<V> list, String string) {
//        return search(list, originContains(string), destinationContains(string), clientContains(string), cargoContains(string));
//    }

    public Predicate<Journey> excludeConcludedJourneys = (x -> !x.isConcluded());

    public Predicate<Journey> excludeCurrentJourneys = (Journey::isConcluded);

//    public Predicate<Journey> journeyLocationContains(String string) {
//        return (x -> x.getContainer().getLocation().getPlaceName().contains(string));
//    }

    public Predicate<Journey> originContains(String string) {
        return (x -> x.getOrigin().getPlaceName().contains(string));
    }

    public Predicate<Journey> destinationContains(String string) {
        return (x -> x.getDestination().getPlaceName().contains(string));
    }

//    --> problem
//    public Predicate<Journey> clientContains(String string) {
//        return (x -> x.getClient().getName().equalsIgnoreCase(string));
//    }
//
//    public Predicate<Journey> cargoContains(String string) {
//        return (x -> x.getCargo().equalsIgnoreCase(string));
//    }
}
