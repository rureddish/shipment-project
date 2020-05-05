package dk.dtu.gbar.gitlab.shipment.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dk.dtu.gbar.gitlab.shipment.Client;
import dk.dtu.gbar.gitlab.shipment.Journey;
import dk.dtu.gbar.gitlab.shipment.Location;
import dk.dtu.gbar.gitlab.shipment.LogIn;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class JourneyRegisterScreen extends JFrame {

	private JPanel panelJourneyRegistration;
	private LoginScreen parentWindow;
	private MainMenuScreen mainMenuScreen;
	private LogIn loggedIn;
	private JButton btnRegister;
	private JButton btnBack;
	private JTextField txtOrigin;
	private JTextField txtDestination;
	private JTextField txtCargo;

	
	public JourneyRegisterScreen(LoginScreen parentWindow,MainMenuScreen mainMenuScreen, LogIn loggedIn) {
		this.parentWindow = parentWindow;
		this.loggedIn = loggedIn;
		this.mainMenuScreen = mainMenuScreen;
		initialize();
	}

	private void initialize() {
		panelJourneyRegistration = new JPanel();
		parentWindow.addPanel(panelJourneyRegistration);
		panelJourneyRegistration.setLayout(null);
		panelJourneyRegistration.setBorder(BorderFactory.createTitledBorder("Journey Registration"));

		btnRegister = new JButton("Register");
		btnRegister.setBounds(10, 260, 150, 29);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtOrigin.getText().isBlank()||txtDestination.getText().isBlank()||txtCargo.getText().isBlank()) {
					System.out.println("Please Fill Out All Spaces");
				}
				else {
					loggedIn.getLoggedInClient().registerNewJourney(new Journey(new Location(txtOrigin.getText()),
							new Location(txtDestination.getText()),loggedIn.getLoggedInClient(),txtCargo.getText()));
				}
			}
		});

		btnBack = new JButton("Back");
		btnBack.setLocation(290, 11);
		btnBack.setSize(150, 29);
		btnBack.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				mainMenuScreen.setVisible(true);
			}
		});

		panelJourneyRegistration.add(btnBack);
		panelJourneyRegistration.add(btnRegister);
	}
	
	private void setEnableButtons(boolean enabled) {
		btnRegister.setEnabled(enabled);


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
		panelJourneyRegistration.setVisible(visible);
	}

}
