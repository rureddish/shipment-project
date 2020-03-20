package dk.dtu.gbar.gitlab.shipment;

import dk.dtu.gbar.gitlab.shipment.persistence.HibernateUtil;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Client;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Container;
import dk.dtu.gbar.gitlab.shipment.persistence.models.ContainerStatus;
import dk.dtu.gbar.gitlab.shipment.persistence.service.ClientService;
import dk.dtu.gbar.gitlab.shipment.persistence.service.ContainerService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

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

        /*ClientService cs = new ClientService();
        ContainerService con = new ContainerService();
        Client alice = cs.getById(0, true);
        Collection<Container> own = alice.getClientsContainers();
        for (Container c : own) {
            List<ContainerStatus> last;
            last = con.getLastStatuses(c);
            for (ContainerStatus s : last) {
                System.out.printf("%s %s %s\n", s.getStatusName(), s.getStatusValue(), s.getDate());
            }
        }*/
        /*ClientService clientService = new ClientService();
        ContainerService cs = new ContainerService();
        Client alice = new Client("First Client", "Alice", "mail@test.com", "World trade center");
        Container aliceContainer = new Container("Container 1", false, alice);
        clientService.save(alice);
        cs.save(aliceContainer);
        Client client = clientService.getById(alice.getId(),true);
        System.out.printf("%s %s %s\n", client.getId(), client.getClientName(), client.getReferencePerson());
        client.getClientsContainers().forEach(c -> System.out.println(c.getName()));*/
        //HibernateUtil.shutdown();
    }
}
