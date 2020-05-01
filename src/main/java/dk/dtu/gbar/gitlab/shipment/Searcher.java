package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public ArrayList<Journey> getCurrentJourneys(Client client){
        return search(client.getJourneys(), excludeConcludedJourneys);
    }

    public ArrayList<Journey> getConcludedJourneys(Client client){
        return search(client.getJourneys(), excludeCurrentJourneys );
    }


    public ArrayList clientSearchByString(ArrayList<Client> list, String string) {
        List allClientStringFieldPredicates = Arrays.asList(clientNameContains(string), emailContains(string), refPersonContains(string), addressContains(string));
        return search(list, allClientStringFieldPredicates);
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

    public Predicate<Journey> excludeConcludedJourneys = (x -> !x.isConcluded());

    public Predicate<Journey> excludeCurrentJourneys = (Journey::isConcluded);


    public Predicate<Journey> originContains(String string) {
        return (x -> x.getOrigin().getPlaceName().contains(string));
    }

    public Predicate<Journey> destinationContains(String string) {
        return (x -> x.getDestination().getPlaceName().contains(string));
    }


    // Journey
//    public ArrayList<V> journeySearchByString(ArrayList<V> list, String string) {
//        return search(list, originContains(string), destinationContains(string), clientContains(string), cargoContains(string));
//    }


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
//    public Predicate<Journey> cargoContains(String string) {
//        return (x -> x.getCargo().equalsIgnoreCase(string));
//    }
}
