package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;

public class Client {
    String name;
    String address;
    String refPerson;
    String email;
    String clientID;
    ResponseObject response;

    public Client(String name, String address, String refPerson, String email) {
        this.name = name;
        this.address = address;
        this.refPerson = refPerson;
        this.email = email;
        this.clientID = "";
    }

    public Client() {

    }

    public ResponseObject addContainer(Container container) {
        if (container.getOwnerID().isEmpty()){
            container.setOwnerID(clientID);
            response = new ResponseObject(0, container.getContainerID() + " added to " + name);
        }
        else{
            response = new ResponseObject(0, "Container already in use");
        }
        return response;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ResponseObject setAddress(String address) {
        this.address = address;
        ResponseObject response = new ResponseObject(0, "address updated");
        return response;
    }

    public void setRefPerson(String refPerson) {
        this.refPerson = refPerson;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getRefPerson() {
        return refPerson;
    }

    public String getEmail() {
        return email;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }
}
