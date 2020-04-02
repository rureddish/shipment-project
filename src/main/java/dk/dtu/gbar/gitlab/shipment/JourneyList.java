package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Stream;

public class JourneyList extends List<Journey> {
    public JourneyList() {
        super();	//the hashMap list is created
    }

//    public ArrayList<Journey> searchByString (String criterium){
//        ArrayList<Journey> results = new ArrayList<>();
//        for (Journey journey: list.values()){
//            if (journey.getClient().getName().equals(criterium)||journey.getCargo().equals(criterium)){
//                results.add(journey);
//            }
//        }
//        return results;
//    }

    @Override
    //override List.add to ensure port of origin has containers
    public void add(Journey journey){
        if (!journey.getOrigin().getPortcontainers().isEmpty()){
        	idNumber++;
            journey.setID(idNumber);
            list.put(idNumber, journey);
            journey.setContainer(journey.getOrigin().getPortcontainers().remove());
//Container leave port
            list.put(idNumber,journey);
        }
        else{
            System.out.println("no containers in port");
        }
    }



    public HashMap<Integer, Journey> getList() {
        return list;
    }
}

