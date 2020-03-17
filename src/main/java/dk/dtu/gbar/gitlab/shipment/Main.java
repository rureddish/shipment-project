package dk.dtu.gbar.gitlab.shipment;


import dk.dtu.gbar.gitlab.shipment.persistence.models.Client;
import dk.dtu.gbar.gitlab.shipment.persistence.service.ClientService;

public class Main {
    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        Client client = clientService.getById(1);
    }
}
