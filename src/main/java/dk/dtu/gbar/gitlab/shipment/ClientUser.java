package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ClientUser {
    Client client;
    Searcher<Journey> search = new Searcher();

    //searches by email because duplicates are prevented
    public ArrayList<Journey> getClientJourneys(ArrayList<Journey> journeylist){
        return search.search(journeylist, journeyClientEmail(client.getEmail()));
    }


    public ArrayList<Journey> getCurrentClientJourneys(ArrayList<Journey> journeylist){
        return search.search(getClientJourneys(journeylist), search.excludeConcludedJourneys );
    }

    public ArrayList<Journey> getConcludedClientJourneys(ArrayList<Journey> journeylist){
        return search.search(getClientJourneys(journeylist), search.excludeCurrentJourneys );
    }


    public Predicate<Journey> journeyClientEmail (String string) {
        return (x -> x.getClient().getEmail().contains(string));
    }

    public ClientUser(Client client) {
        this.client = client;
    }
}
