package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList; 
import java.util.List;
import java.util.function.Predicate;

public class ClientUser {
    Client client;
    Searcher<Journey> search;
    EntityList<Journey> journeyList;

    //Constructor
    public ClientUser(Client client) {
    	this.client = client;
    	search = new Searcher();
    }
    
    //Getters and Setters
    public void setJourneyList(EntityList<Journey> journeyList) {
    	this.journeyList = journeyList;
    } 

    //searches by email because duplicates are prevented
    public ArrayList<Journey> getClientJourneys(){
        return search.search(journeyList.getList(), journeyClientEmail(client.getEmail()));
    }

    public ArrayList<Journey> getCurrentClientJourneys(){
        return search.search(getClientJourneys(), search.excludeConcludedJourneys );
    }

    public ArrayList<Journey> getConcludedClientJourneys(){
        return search.search(getClientJourneys(), search.excludeCurrentJourneys );
    }

    public Predicate<Journey> journeyClientEmail (String string) {
        return (x -> x.getClient().getEmail().contains(string));
    }

}
