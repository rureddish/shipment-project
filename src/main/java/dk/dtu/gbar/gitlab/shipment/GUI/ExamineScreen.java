package dk.dtu.gbar.gitlab.shipment.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dk.dtu.gbar.gitlab.shipment.LogIn;
import dk.dtu.gbar.gitlab.shipment.LogisticsCompany;
import dk.dtu.gbar.gitlab.shipment.persistence.models.Journey;
import dk.dtu.gbar.gitlab.shipment.persistence.service.ContainerService;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;

public class ExamineScreen extends JFrame {

	private LoginScreen parentWindow;
	private ClientScreen clientScreen;
	private JPanel panelExamine;
	private LogIn loggedIn;
	private LogisticsCompany logisticsCompany;
	private int journeyID;
	private JButton btnBack;
	private JLabel lblJourneyStatus;
	private int journeyIndex;
	
	
	public ExamineScreen(LoginScreen parentWindow, ClientScreen clientScreen, LogIn loggedIn, LogisticsCompany logisticsCompany, int journeyID, int journeyIndex) {
		this.parentWindow = parentWindow;
		this.clientScreen = clientScreen;
		this.loggedIn = loggedIn;
		this.logisticsCompany = logisticsCompany;
		this.journeyID = journeyID;
		this.journeyIndex = journeyIndex;
		initialize();
	}
	private void initialize() {
		panelExamine = new JPanel();
		parentWindow.addPanel(panelExamine);
		panelExamine.setLayout(null);
		panelExamine.setBorder(BorderFactory.createTitledBorder("Examine"));
		
		btnBack = new JButton("Back");
		btnBack.setLocation(290, 11);
		btnBack.setSize(150, 29);
		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				setEnableButtons(false);
				clientScreen.setVisible(true);
				
			}
		});
		lblJourneyStatus = new JLabel("Journey Status for Journey ID " + journeyID);
 		lblJourneyStatus.setBounds(10, 26, 192, 14);
 		Journey currentJourney = logisticsCompany.getJourneys().get(journeyIndex);
 		ContainerService cs = new ContainerService();
 		JTextArea txtStatus;
 		txtStatus = new JTextArea(
	 				"Journey Container ID: " + currentJourney.getJourneyContainer().getName() +"\n"+
	 				"Container Contents: " + currentJourney.getContainerContent() +"\n"+
	 				"Container Status: NA" 
	 				
	 				);
 		
		txtStatus.setBounds(10, 85, 430, 204);
		
		
		panelExamine.add(lblJourneyStatus);
		panelExamine.add(btnBack);
		
		
		
		panelExamine.add(txtStatus);
	}
	
	private void setEnableButtons(boolean enabled) {
		
		btnBack.setEnabled(enabled);


	}
	
	private void enableButtons() {
		setEnableButtons(true);
	}

	private void disableButtons() {
		setEnableButtons(false);
	}
	
	public void setVisible(boolean visible) {
		if (!visible) {
			disableButtons();
		}
		else {
			enableButtons();
		}
		panelExamine.setVisible(visible);
	}
}
	

