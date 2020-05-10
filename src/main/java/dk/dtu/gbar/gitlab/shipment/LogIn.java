package dk.dtu.gbar.gitlab.shipment;

import dk.dtu.gbar.gitlab.shipment.persistence.models.Client;
import dk.dtu.gbar.gitlab.shipment.persistence.search.SearchCriteria;
import dk.dtu.gbar.gitlab.shipment.persistence.service.ClientService;

import java.util.List;

/**
 * Checks email and password for login and tracks which client is logged in.
 */
public class LogIn {
    private LogisticsCompany logisticsCompany;
    private Client loggedInClient;
    private Boolean adminLoggedIn = false;
    private Searcher searcher = new Searcher(logisticsCompany);
    private ClientService cs = new ClientService();


    //Constructor
    public LogIn(LogisticsCompany logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    /**
     * Checks if input email belongs to a client and if the password matches for that client. Returns true if both are true.
     *
     * @param email    The email entered into the login screen.
     * @param password The password entered in login screen.
     */
//    public boolean checkClientLogin(String email, String password) {
//        ArrayList<Client> client = (searcher.search(logisticsCompany.getClientList(), searcher.emailContains(email)));
//        if (client.size() == 0) {
//            System.out.println("Wrong email");
//            return false;
//        } else {
//            if (client.get(0).getPassword().equals(password)) {
//                loggedInClient = client.get(0);
//                return true;
//            } else {
//                System.out.println("Wrong password");
//                return false;
//            }
//        }
//    }
    public boolean loginClient(String email, String password) {
        List<Client> search = cs.search(new SearchCriteria("email", email));
        //ArrayList<Client> client = (searcher.search(logisticsCompany.getClientList(), searcher.emailContains(email)));
        if (search.size() == 0) {
            System.out.println("Wrong email");
            return false;
        }
        Client client = search.get(0);
        if (Bcrypt.checkPassword(password, client.getPassword())) {
            loggedInClient = client;
            return true;
        }
        return false;
    }
    
   /* public boolean checkClientPassword(Client client, String password) {
    	ArrayList<Client> client = (searcher.search(logisticsCompany.getClientList(), searcher.emailContains(email)));
    	if (client.get(0).getPassword().equals(password)) {
            loggedInClient = client.get(0);
            return true;
    	}
        return false;
    }*/

    public boolean adminLogIn(String username, String passwordTest) {
        if (username.equals("admin")) {
            if (logisticsCompany.getPassword().equals(passwordTest)) {
                adminLoggedIn = true;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void logOut() {
        loggedInClient = null;
    }

    //Getters and Setters
    public Client getLoggedInClient() {
        return loggedInClient;
    }

    public Boolean isAdminLoggedIn() {
        return adminLoggedIn;
    }

}
