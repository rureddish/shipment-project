package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Database {
    private ClientList clientList = new ClientList();
    private ContainerList containerList = new ContainerList();
    private JourneyList journeyList = new JourneyList();

    public Database() {
    }

    public ClientList getClientList() {
        return clientList;
    }

    public ContainerList getContainerList() {
        return containerList;
    }

    public JourneyList getJourneyList() {
        return journeyList;
    }
}