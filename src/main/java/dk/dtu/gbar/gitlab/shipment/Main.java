package dk.dtu.gbar.gitlab.shipment;

import dk.dtu.gbar.gitlab.shipment.persistence.HibernateUtil;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Client;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Container;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Status;
import dk.dtu.gbar.gitlab.shipment.persistence.service.ClientService;
import dk.dtu.gbar.gitlab.shipment.persistence.service.ContainerService;

import java.util.*;

public class Main {
    public static void main(String[] args) {
      /*  ClientService clientService = new ClientService();
        Client alice = new Client("Alice", "Test", "mail@domain.com", "Street 1");
        clientService.save(alice);
        Client client = clientService.getById(alice.getId());
        System.out.printf("%s %s %s\n", client.getId(), client.getFirstName(), client.getLastName());
        Client bob = new Client("Bob", "Test", "bob@mail.com", "Street 2");
        clientService.save(bob);
        Client search = clientService.getById(bob.getId());
        System.out.printf("%s %s %s\n", search.getId(), search.getFirstName(), search.getLastName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
      /*
        ClientService cs = new ClientService();
        ContainerService con = new ContainerService();
        Client alice = cs.getById(1,true);
        Set<Container> own = alice.getContainers();
        for(Container c : own){
            List<Status> last;
            last =con.getLastStatuses(c);
            for (Status s : last){
                System.out.printf("%s %s %s\n",s.getStatusName(),s.getStatusValue(),s.getDate());
            }
        }*/
        HibernateUtil.shutdown();
    }
}
