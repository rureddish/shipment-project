package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Database {
    private ClientList clientList;
    private ContainerList containerList;
    private JourneyList journeyList;

    public Database() {
        this.clientList = new ClientList();
        this.containerList = new ContainerList();
        this.journeyList = new JourneyList();
    }

    public JourneyList getJourneyList() {
		return journeyList;
	}

	public void setJourneyList(JourneyList journeyList) {
		this.journeyList = journeyList;
	}

	public ClientList getClientList() {
        return clientList;
    }

//Maybe useless to have a setter
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