package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;

public class LogIn {
    LogisticsCompany logisticsCompany;
    Client loggedInClient;
    Boolean adminLoggedIn=false;
    Searcher<Client> searcher = new Searcher();

    //Constructor
    public LogIn(LogisticsCompany logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public boolean clientLogIn(String emailTest, String passwordTest) {
        ArrayList<Client> client = (searcher.search(logisticsCompany.getClientList(), searcher.emailContains(emailTest)));
        if (client.size() == 0) {
            System.out.println("Wrong email");
            return false;
        } else {
            if (client.get(0).getPassword().equals(passwordTest)) {
                loggedInClient = client.get(0);
                return true;
            } else {
                System.out.println("Wrong password");
                return false;
            }
        }
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
