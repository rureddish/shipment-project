package dk.dtu.gbar.gitlab.shipment;

import dk.dtu.gbar.gitlab.shipment.GUI.LoginScreen;
import dk.dtu.gbar.gitlab.shipment.persistence.HibernateUtil;
import dk.dtu.gbar.gitlab.shipment.persistence.models.*;
import dk.dtu.gbar.gitlab.shipment.persistence.service.*;

import java.awt.EventQueue;
import java.util.List;

public class Main {

    public static void initialLogins(LogisticsCompany logisticsCompany) {
        Port hongkong = new Port("Hong kong", "0");
        Port berlin = new Port("Berlin", "0");
        Port newyork = new Port("New York", "0");
        Port tokyo = new Port("Tokyo", "0");
        Port london = new Port("London", "0");
        Port copenhagen = new Port("Copenhagen", "0");
        logisticsCompany.register(tokyo);
        logisticsCompany.register(newyork);
        logisticsCompany.register(berlin);
        logisticsCompany.register(hongkong);
        logisticsCompany.register(copenhagen);
        logisticsCompany.register(london);
        logisticsCompany.register(new Container("A", hongkong));
        logisticsCompany.register(new Container("B", hongkong));
        logisticsCompany.register(new Container("C", hongkong));
        logisticsCompany.register(new Container("D", tokyo));
        logisticsCompany.register(new Container("E", tokyo));
        logisticsCompany.register(new Container("F", tokyo));
        logisticsCompany.register(new Container("G", berlin));
        logisticsCompany.register(new Container("H", berlin));
        logisticsCompany.register(new Container("I", berlin));
        logisticsCompany.register(new Container("J", newyork));
        logisticsCompany.register(new Container("K", london));
        //ClientOld maersk = new ClientOld("Maersk", "Havnepromenaden 42", "A.P. McKinney Maersk Møller", "m", "m");
        Client maersk = new Client("Maersk", "Havnepromenaden 42", "A.P. McKinney Maersk Møller", "m", Bcrypt.hashPassword("m"));
        logisticsCompany.register(maersk);
        Client amazon = new Client("Amazon", "1620 26th Street", "Jeff Bezos", "a", Bcrypt.hashPassword("a"));
        logisticsCompany.register(amazon);
        logisticsCompany.register(new Client("New Egg", "1234 Street st", "Fred Chang", "Newegg@gmail.com", Bcrypt.hashPassword("NewEggPass")));
        Client maersk2 = new Client("Maersk2", "Havnepromenaden 42", "A.P. McKinney Maersk Møller", "m2", Bcrypt.hashPassword("m"));
        //logisticsCompany.register(maersk2);logisticsCompany.register(new Journey(hongkong,copenhagen, amazon, "worker's rights"));
		//logisticsCompany.register(new Journey(tokyo,london,amazon, "Giant Teeth"));
		//logisticsCompany.register(new Journey(newyork, london, maersk, "babushka containers"));
        logisticsCompany.register("worker's rights", amazon, hongkong, copenhagen);
        logisticsCompany.register("Giants Teeth", amazon, tokyo, london);
        logisticsCompany.register("babuska containers", maersk, newyork, london);
        logisticsCompany.register("content", amazon, hongkong, copenhagen);
        //amazon.getJourneys().get(0).endJourney();

    }

    public static void main(String[] args) {
        Client maersk = new Client("Maersk2", "Havnepromenaden 42", "A.P. McKinney Maersk Møller", "m", Bcrypt.hashPassword("m"));
        ClientService cs = new ClientService();
        cs.save(maersk);
        PortService ps = new PortService();
        Port hongkong = new Port("Hong kong2", "0");
        ps.save(hongkong);
        ContainerService con = new ContainerService();
        Container container = new Container("LULW");
        con.save(container);
        container.setName("CHANGED");
        JourneyService js = new JourneyService();
        Journey journey = new Journey("CONTENT",container,maersk,hongkong,hongkong);
        js.save(journey);
        ContainerStatusService css = new ContainerStatusService();
        List<ContainerStatus> statusList = con.getLastStatuses(container);
        ContainerStatus stat1 = new ContainerStatus(ContainerStatusName.TEMPERATURE,"10",journey);
        ContainerStatus stat2 = new ContainerStatus(ContainerStatusName.HUMIDITY,"10",journey);
        ContainerStatus stat3 = new ContainerStatus(ContainerStatusName.PRESSURE,"10",journey);
        css.save(stat1);
        css.save(stat2);
        css.save(stat3);
        //List<ContainerStatus> statusList = con.getLastStatuses(container);
        for(ContainerStatus s: statusList){
            System.out.println(s.getStatusName());
            System.out.println(s.getStatusValue());
        }
        HibernateUtil.shutdown();
        LogisticsCompany logisticsCompany = new LogisticsCompany("admin");
        initialLogins(logisticsCompany);
        EventQueue.invokeLater(() -> {
            try {
                LoginScreen loginScreen = new LoginScreen(logisticsCompany);
                loginScreen.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        HibernateUtil.shutdown();
    }
}
    	 











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
