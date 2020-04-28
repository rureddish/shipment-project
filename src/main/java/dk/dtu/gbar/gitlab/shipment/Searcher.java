package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Searcher<V extends Entity> {
    // search list by predicates

    @SafeVarargs
    protected final ArrayList<V> search(ArrayList list, Predicate... SearchPredicates){
        ArrayList<Predicate<V>> predicates = new ArrayList(Arrays.asList((SearchPredicates)));
        return (ArrayList<V>) list.stream()
                .filter(predicates.stream().reduce(x-> false, Predicate::or))
                .collect(Collectors.toList());
    } 

      //Container
    protected ArrayList<V> containerSearchByString(ArrayList<Container> list, String string){
        return search(list, locationContains(string));
    }

    protected Predicate<Container> locationContains(String string) {
        return (str -> str.getLocation().getPlaceName().contains(string));
    }

    //Client
    protected ArrayList<V> clientSearchByString(ArrayList<Client> list, String string) {
        return search(list, clientNameContains(string), emailContains(string), refPersonContains(string), addressContains(string));
    }

    protected Predicate<Client> clientNameContains(String string) {
        return (str -> str.getName().contains(string));
    }

    protected Predicate<Client> emailContains(String string) {
        return (str -> str.getEmail().contains(string));
    }

    protected Predicate<Client> refPersonContains(String string) {
        return (str -> str.getRefPerson().contains(string));
    }

    protected Predicate<Client> addressContains(String string) {
        return (str -> str.getAddress().contains(string));
    }

    // Journey
    protected ArrayList<V> journeySearchByString(ArrayList<V> list, String string) {
        return search(list, originContains(string), destinationContains(string), clientContains(string), cargoContains(string)); 
    }            

    protected Predicate<Journey> excludeConcludedJourneys = (x -> !x.isConcluded());

    protected Predicate<Journey> excludeCurrentJourneys = (Journey::isConcluded);

    protected Predicate<Journey> journeyLocationContains(String string) {
        return (x -> x.getContainer().getLocation().getPlaceName().contains(string));
    }

    protected Predicate<Journey> originContains(String string) {
        return (x -> x.getOrigin().getPlaceName().contains(string));
    }

    protected Predicate<Journey> destinationContains(String string) {
        return (x -> x.getDestination().getPlaceName().contains(string));
    }

//    --> problem
    protected Predicate<Journey> clientContains(String string) {
        return (x -> x.getClient().getName().equalsIgnoreCase(string));
    }

    protected Predicate<Journey> cargoContains(String string) {
        return (x -> x.getCargo().equalsIgnoreCase(string));
    }
}
