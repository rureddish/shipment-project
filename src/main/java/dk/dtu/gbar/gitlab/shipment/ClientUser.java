package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.function.Predicate;

public class ClientUser {
    Client client;
    Searcher<Journey> search = new Searcher();
    LogisticsCompany logisticsCompany;

    public ClientUser(Client client) {
        this.client = client;
    }

    //searches by email because duplicates are prevented
    public ArrayList<Journey> getClientJourneys(){
        return client.getJourneys();
    }

    public ArrayList<Journey> getCurrentClientJourneys(){
        return search.search(client.getJourneys(), search.excludeConcludedJourneys );
    }

    public ArrayList<Journey> getConcludedClientJourneys(){
        return search.search(client.getJourneys(), search.excludeCurrentJourneys );
    }
}
