package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ClientList {
    private ArrayList<Client> list;

    public ClientList() {
        this.list = new ArrayList<Client>();
    }

    public ResponseObject addClient(Client client)  {
        if (list.contains(client)){
            ResponseObject response = new ResponseObject(0, "client already exists");
            System.out.println("Client could not be registered, a client by this name already exists");
            return response;
        }
        else if (client.getName()==null||client.getAddress()==null||client.getEmail()==null||client.getRefPerson()==null){
            ResponseObject response = new ResponseObject(0, "client info missing");
            System.out.println("Client could not be registered, info is missing");
            return response;
        }
        else{
            list.add(client);
            return new ResponseObject(0, "client added");
        }
    }

    public ResponseObject remove(Client c) {
        list.remove(c);
        System.out.println("client has been removed");
        return new ResponseObject(0, "client removed");
    }

    public ResponseObject searchClient(String criterium) {
        ArrayList<Client> clients = list.stream().filter(c -> c.email.contains(criterium)
                || c.address.contains(criterium)
                || c.name.contains(criterium)
                || c.refPerson.contains(criterium))
                .collect(Collectors.toCollection(ArrayList::new));
        if (clients.size()>0){
            String errorMessage = clients.get(0).getName();
            return new ResponseObject(0, errorMessage);
        }
        else {
            return new ResponseObject(0,"No clients found");
        }
    }

}
