package dk.dtu.gbar.gitlab.shipment;

public class Client {
    String name;
    String address;
    String refPerson;
    String email;

    public Client(String name, String address, String refPerson, String email) {
        this.name = name;
        this.address = address;
        this.refPerson = refPerson;
        this.email = email;
    }

    public Client() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
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
