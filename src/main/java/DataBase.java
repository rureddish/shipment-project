import java.util.ArrayList;

public class DataBase {
    public DataBase() {
    }

    ArrayList<Client> ClientList;
    private ArrayList<Client> SearchResult;

    public ArrayList<Client> searchClient(String criterium){
        SearchResult = new ArrayList<Client>();
        for (Client c:ClientList){
            if (c.Address.equals(criterium)
                    ||c.Email.equals(criterium)
                    ||c.Name.equals(criterium)
                    ||c.RefPerson.equals(criterium)){
                SearchResult.add(c);
            }
        }
        if (SearchResult.isEmpty()){
            System.out.println("No clients found");
        }
        return SearchResult;
    }
}
