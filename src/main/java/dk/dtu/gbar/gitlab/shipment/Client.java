package dk.dtu.gbar.gitlab.shipment;
import java.util.ArrayList;

/**
 * Entity containing basic client information and password.
 * Contains list of Journeys registered by the client.
 */
public class Client {
    private String name;
    private String address;
    private String refPerson;
    private String email;
    private String password;
    private ArrayList<Journey> journeys = new ArrayList<>();

    /**
     @param name The name of the client
     @param address The mailing address of the client
     @param refPerson The reference person of the client
     @param email The Email address of the client
     @param password The password to access the client's data
     */
    public Client(String name, String address, String refPerson, String email, String password) {
        this.name = name;
        this.address = address;
        this.refPerson = refPerson;
        this.email = email;
        this.password= password;
    }

    // setters and getters
    public ArrayList<Journey> getJourneys() {
        return journeys;
    }
    
    public void registerNewJourney(Journey journey) {
    	journeys.add(journey);
    }

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
    
    public String getPassword() {
    	return password;
    }

}
