package dk.dtu.gbar.gitlab.shipment;

import java.util.ArrayList;

public class LogIn {
	ClientList clientList;
	Client loggedInClient;
	Searcher<Client> searcher = new Searcher();
	
	//Constructor
	public LogIn(ClientList clientList) {
		this.clientList = clientList;
	}

	public void logIn(String emailTest, String passwordTest) {
		ArrayList<Client> client = (searcher.search(clientList.getList(), searcher.emailContains(emailTest)));
//		ArrayList<Client> client = searcher.clientSearchByString(clientList.getList(),emailTest);
		if (client.size()==0) {
			System.out.println("Wrong email");
		}
		else { 
			if (client.get(0).getPassword().equals(passwordTest)) {
				loggedInClient = client.get(0);
			}
			else {
				System.out.println("Wrong password");
			}
		}
	}

	public void logOut(){
		loggedInClient=null;
	}
	//Getters and Setters
	public Client getLoggedInClient() {
		return loggedInClient;
	}
	

}
