package dk.dtu.gbar.gitlab.shipment.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dk.dtu.gbar.gitlab.shipment.Client;

public class JourneyRegisterScreen extends JFrame {

	private JPanel panelJourneyRegistration;
	private LoginScreen parentWindow;
	private MainMenuScreen mainMenuScreen;
	private Client loggedIn;
	private JButton btnRegister;
	private JButton btnBack;
	
	
	public JourneyRegisterScreen(LoginScreen parentWindow,MainMenuScreen mainMenuScreen, Client loggedIn) {
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
