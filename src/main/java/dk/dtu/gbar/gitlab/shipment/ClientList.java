package dk.dtu.gbar.gitlab.shipment;

import java.util.HashMap;
import java.util.stream.Stream;

public class ClientList extends EntityList<Client> {


    @Override 
    public void add(Client client) {
        if (!clientEmailAlreadyInUse(client)) {
            super.add(client);
        }
    }

    Searcher<Client> search = new Searcher<>();
    public boolean clientEmailAlreadyInUse(Client client) {
        return 0 < search.search(list, search.emailContains(client.getEmail())).size();
    }

    public ClientList() {
        super();

    }

}
