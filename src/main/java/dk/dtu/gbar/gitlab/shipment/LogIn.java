package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;

/**
 * Checks email and password for login and tracks which client is logged in.
 */
public class LogIn {
    LogisticsCompany logisticsCompany;
    Client loggedInClient;
    Boolean adminLoggedIn = false;
    Searcher searcher = new Searcher(logisticsCompany);

    //Constructor
    public LogIn(LogisticsCompany logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    /**
     * Checks if input email belongs to a client and if the password matches for that client. Returns true if both are true.
     * @param email The email entered into the login screen.
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
    
    public boolean checkClientEmail(String email) {
    	ArrayList<Client> client = (searcher.search(logisticsCompany.getClientList(), searcher.emailContains(email)));
        if (client.size() == 0) {
            System.out.println("Wrong email");
            return false;
        }
        return true;
    }
    
    public boolean checkClientPassword(String email, String password) {
    	ArrayList<Client> client = (searcher.search(logisticsCompany.getClientList(), searcher.emailContains(email)));
    	if (client.get(0).getPassword().equals(password)) {
            loggedInClient = client.get(0);
            return true;
    	}
        return false;
    }

    public boolean adminLogIn(String passwordTest) {
        if (logisticsCompany.getPassword().equals(passwordTest)) {
            adminLoggedIn = true;
            return true;
        } else {
            System.out.println("Wrong password");
            return false;
        }

    }

    public void logOut() {
        loggedInClient = null;
    }

    //Getters and Setters
    public Client getLoggedInClient() {
        return loggedInClient;
    }


}
