package dk.dtu.gbar.gitlab.shipment;

import dk.dtu.gbar.gitlab.shipment.persistence.models.*;
import dk.dtu.gbar.gitlab.shipment.persistence.search.SearchCriteria;
import dk.dtu.gbar.gitlab.shipment.persistence.service.ClientService;
import dk.dtu.gbar.gitlab.shipment.persistence.service.ContainerService;
import dk.dtu.gbar.gitlab.shipment.persistence.service.JourneyService;
import dk.dtu.gbar.gitlab.shipment.persistence.service.PortService;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Database of all objects in the system.
 * Contains list of clients, containers, journeys, locations and ships.
 * Registers objects to these lists, so they are in the system and their
 * index in the list acts as ID.
 */
public class LogisticsCompany {
    private String password;
    private ArrayList<Ship> shipList = new ArrayList<>();
    private ClientService cs = new ClientService();
    private ContainerService cos = new ContainerService();
    private PortService ps = new PortService();
    private JourneyService js = new JourneyService();

    PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * @param password Password for logging in as the logistics company.
     */
    public LogisticsCompany(String password) {
        this.password = password;
    }


    /**
     * Registers client if email not in use, returns false otherwise
     *
     * @param userName  user name of the client
     * @param address   address
     * @param refPerson reference person
     * @param email     email that should not be in use
     * @param password  password in plaintext
     * @return whether the register was successful
     */
    public boolean register(String userName, String address, String refPerson, String email, String password) {
        if (!clientEmailAlreadyInUse(email)) {
            Client client = new Client(userName, address, refPerson, email, Bcrypt.hashPassword(password));
            cs.save(client);
            //clientList.add(client);
            return true;
        }
        return false;
    }

    public boolean register(Port port) {
        Collection<Port> search = ps.search(new SearchCriteria("name", port.getName()));

        if (ps.search(new SearchCriteria("name", port.getName())).size() > 0) {
            return false;
        }
        ps.save(port);
        return true;
    }

    public boolean register(Client client) {
        if (!clientEmailAlreadyInUse(client.getEmail())) {
            cs.save(client);
            return true;
        }
        return false;
    }

    public boolean register(Container container) {
        if (cos.search(new SearchCriteria("name", container.getName())).size() > 0) {
            return false;
        }
        cos.save(container);
        return true;
        /*container.setID(containerList.size());
        containerList.add(container);*/
    }

    /**
     * if there are containers at origin journey is registered.
     * returns false if not successful
     *
     * @param
     * @return
     */
   /* public boolean register(Journey journey) {
        if (journeyOriginHasContainers(journey)) {
            journey.setID(journeyList.size());
            journeyList.add(journey);
            journey.setContainer(journey.getOrigin().getLocationContainers().remove());
            journey.getContainer().getJourneyHistory().add(journey);
            journey.getClient().getJourneys().add(journey);

            support.firePropertyChange("Journey Added", null, null);
            return true;
        } else {
            return false;
        }
    }*/
    public boolean register(String origin, String destination, Client loggedInClient, String content) {
        Port originPort = ps.search(new SearchCriteria("name", origin)).get(0);
        Collection<Container> containers = originPort.getPortContainers();
        if (containers.size() > 0) {
            Container container = containers.iterator().next();
            container.setContainerLocation(null);
            Port destinationPort = ps.search(new SearchCriteria("name", destination)).get(0);
            Journey journey = new Journey(content, container, null, loggedInClient, originPort, destinationPort, originPort, destinationPort);
            js.save(journey);
            support.firePropertyChange("Journey Added", null, null);
            return true;
        }
        return false;
    }

    public boolean register(String content, Client client, Port origin, Port destination) {
        Collection<Container> containers = origin.getPortContainers();
        if (containers != null) {
            Container container = containers.iterator().next();
            container.setContainerLocation(null);
            Journey journey = new Journey(content, container, client, origin, destination);
            js.save(journey);
            support.firePropertyChange("Journey Added", null, null);
            return true;
        }
        return false;
    }


    public void register(Ship ship) {
        shipList.add(ship);
    }

    public void removeClient(Client client) {
		/*client.setAddress("Redacted");
		client.setRefPerson("Redacted");
		client.setName("Redacted");
		client.setEmail("Redacted");*/
        client.setClientStatus(ClientStatus.DELETED);
    }

    public boolean clientEmailAlreadyInUse(String email) {
        return 0 < cs.search(new SearchCriteria("email", email)).size();
        //return 0 < search.search(clientList, search.emailContains(client.getEmail())).size();
    }

    /*public boolean journeyOriginHasContainers(Journey object) {
        return 0 < object.getOrigin().getLocationContainers().size();
    }*/

    public List<Port> getPorts() {
        return ps.getAll();
    }

    //Getters and Setters


    public String getPassword() {
        return password;
    }

    public ArrayList<Ship> getShipList() {
        return shipList;
    }

    public void addObserver(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public List<Journey> getJourneys() {
        return js.getAll();
    }

    public List<Client> getClients() {
        return cs.getAll();
    }
}