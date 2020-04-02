package dk.dtu.gbar.gitlab.shipment;

public class Client extends Entity {
    String name;
    String address;
    String refPerson;
    String email;
    Integer clientID;

    public Client(String name, String address, String refPerson, String email) {
        this.name = name;
        this.address = address;
        this.refPerson = refPerson;
        this.email = email;
    }

    // setters and getters
    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
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


}
