package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class JourneyList {
    private ArrayList<Journey> list;

    public JourneyList() {
        this.list = new ArrayList<Journey>();
    }

    public ResponseObject searchJourney(String criterium) {
        ArrayList<Journey> journeys = list.stream().filter(c -> c.getJourneyID().contains(criterium)
                || c.getPortOfOrigin().contains(criterium)
                || c.getPortOfDestination().contains(criterium)
                || c.getCompany().contains(criterium))
                .collect(Collectors.toCollection(ArrayList::new));
        if (journeys.size() > 0) {
            String search = journeys.get(0).getJourneyID();
            return new ResponseObject(0, search);
        } else {
            return new ResponseObject(0, "No journey found");
        }
    }

    public ResponseObject addJourney(Journey j) {
        list.add(j);
        ResponseObject response = new ResponseObject(0, "Journey added");
        return response;
    }

    public ResponseObject remove(Container container) {
        list.remove(container);
        System.out.println("client has been removed");
        ResponseObject response = new ResponseObject(0, "client removed");
        return response;
    }

    public ArrayList<Journey> getList() {
        return list;
    }

    public void setList(ArrayList<Journey> list) {
        this.list = list;
    }


}

