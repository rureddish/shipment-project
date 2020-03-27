package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Database {

    private ArrayList<Client> clientList;

    public Database() {
        this.clientList = new ArrayList<>();
    }

    public ArrayList<Client> searchClient(String criterium) {
        return clientList.stream().filter(c -> c.email.contains(criterium)
                || c.address.contains(criterium)
                || c.name.contains(criterium)
                || c.refPerson.contains(criterium))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ResponseObject addClient(Client c)  {
        if (searchClient(c.name).contains(c)){
            ResponseObject response = new ResponseObject(0, "client already exists");
            System.out.println("Client could not be registered, a client by this name already exists");
            return response;
        }
        else if (c.getName()==null||c.getAddress()==null||c.getEmail()==null||c.getRefPerson()==null){
            ResponseObject response = new ResponseObject(0, "client info missing");
            System.out.println("Client could not be registered, info is missing");
            return response;
        }
        else{
            clientList.add(c);
            ResponseObject response = new ResponseObject(0, "client added");
            return response;
        }
    }

    public ResponseObject remove(Client c) {
        clientList.remove(c);
        System.out.println("client has been removed");
        ResponseObject response = new ResponseObject(0, "client removed");
        return response;
    }
    public static class incompleteClientError extends Exception{
        public incompleteClientError(){
            System.out.println("Client could not be registered, info is missing");
        }
    }
    public static class clientAlreadyExistsError extends Exception{
        public clientAlreadyExistsError(){
            System.out.println("Client could not be registered, a client by this name already exists");
        }
    }
}