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

    public void addClient(Client c) throws incompleteClientError, clientAlreadyExistsError {
        if (searchClient(c.name).contains(c)){
            System.out.println("Client could not be registered, a client by this name already exists");
        }
        else if (c.getName()==null||c.getAddress()==null||c.getEmail()==null||c.getRefPerson()==null){
            System.out.println("Client could not be registered, info is missing");
        }
        else{
            clientList.add(c);

        }

    }

    public void remove(Client properclient) {

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