package dk.dtu.gbar.gitlab.shipment;

import java.util.List;
import java.util.function.Predicate;

public class ClientList extends EntityList<Client> {
    public ClientList() {
        super();
    }

    // search all relevant fields by string
    public List<Client> searchByString(String string) {
        return search(nameContains(string), emailContains(string), refPersonContains(string), addressContains(string));
    }

    // search predicates
    public Predicate<Client> nameContains(String string) {
        return (str -> str.getName().contains(string));
    }

    public Predicate<Client> emailContains(String string) {
        return (str -> str.getEmail().contains(string));
    }

    public Predicate<Client> refPersonContains(String string) {
        return (str -> str.getRefPerson().contains(string));
    }

    public Predicate<Client> addressContains(String string) {
        return (str -> str.getAddress().contains(string));
    }


}
