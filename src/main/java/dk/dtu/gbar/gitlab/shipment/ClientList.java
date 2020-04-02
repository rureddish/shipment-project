package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.HashMap;

public class ClientList extends List<Client>{
    //private HashMap<Integer, Client> list;
    public ClientList() {
        super(); 
    }

    public ArrayList<Client> searchByString (String criterium){
        //search name, refperson, email and address
        ArrayList<Client> results = new ArrayList<>();
        for (Client client: list.values()){
            if (client.getName().equals(criterium)||client.getRefPerson().equals(criterium)||client.getEmail().equals(criterium)||client.getAddress().equals(criterium)){
                results.add(client);
            }
        }
        return results;
    }


}
