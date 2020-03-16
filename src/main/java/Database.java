import java.util.ArrayList;
import java.util.stream.Collectors;

public class Database {

    private ArrayList<Client> clientList;

    public Database() {
        this.clientList = new ArrayList<>();
    }

    public ArrayList<Client> searchClient(String criterium) {
        return clientList.stream().filter(c -> c.email.equals(criterium)
                || c.address.equals(criterium)
                || c.name.equals(criterium)
                || c.refPerson.equals(criterium))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void addClient(Client c) {
        clientList.add(c);
    }
}
