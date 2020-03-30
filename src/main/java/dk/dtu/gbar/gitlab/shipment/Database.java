package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Database {
    private ClientList clientList;
    private ContainerList containerList;


    public Database() {
        this.clientList = new ClientList();
        this.containerList = new ContainerList();
    }

    public ClientList getClientList() {
        return clientList;
    }

    public void setClientList(ClientList clientList) {
        this.clientList = clientList;
    }

    public ContainerList getContainerList() {
        return containerList;
    }

    public void setContainerList(ContainerList containerList) {
        this.containerList = containerList;
    }
}