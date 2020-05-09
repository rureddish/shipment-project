package dk.dtu.gbar.gitlab.shipment.GUI;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dk.dtu.gbar.gitlab.shipment.LogIn;
import dk.dtu.gbar.gitlab.shipment.LogisticsCompany;

public class ExamineScreen extends JFrame{

	private LoginScreen parentWindow;
	private ClientScreen clientScreen;
	private JPanel panelExamine;
	private LogIn loggedIn;
	private LogisticsCompany logisticsCompany;
	private int containerID;
	
	public ExamineScreen(LoginScreen parentWindow, ClientScreen clientScreen, LogIn loggedIn, LogisticsCompany logisticsCompany, int containerID) {
		this.parentWindow = parentWindow;
		this.clientScreen = clientScreen;
		this.loggedIn = loggedIn;
		this.logisticsCompany = logisticsCompany;
		this.containerID = containerID;
		initialize();
	}
	private void initialize() {
		panelExamine = new JPanel();
		parentWindow.addPanel(panelExamine);
		panelExamine.setLayout(null);
		panelExamine.setBorder(BorderFactory.createTitledBorder("Examine"));
		
		
		
	}
	
}
