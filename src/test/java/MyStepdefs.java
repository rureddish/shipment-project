import dk.dtu.gbar.gitlab.shipment.Client;
import dk.dtu.gbar.gitlab.shipment.ClientList;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;

import java.util.List;

public class MyStepdefs {
    Client client1;
    ClientList clientList;
    List searchresults;



    @Given("a Client {string} with address {string} email {string} and ref person {string}")
    public void aClientWithAddressEmailAndRefPerson(String name, String address, String email, String refperson) {
        client1=new Client(name,address,email,refperson);
    }

    @And("an empty client list")
    public void anEmptyClientList() {
    }
}
