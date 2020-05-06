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
import dk.dtu.gbar.gitlab.shipment.Journey;
import dk.dtu.gbar.gitlab.shipment.Location;
import dk.dtu.gbar.gitlab.shipment.LogIn;
import dk.dtu.gbar.gitlab.shipment.LogisticsCompany;

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
	private LogisticsCompany logisticsCompany;

	
	public JourneyRegisterScreen(LoginScreen parentWindow,MainMenuScreen mainMenuScreen, LogIn loggedIn, LogisticsCompany logisticsCompany) {
		this.parentWindow = parentWindow;
		this.loggedIn = loggedIn;
		this.mainMenuScreen = mainMenuScreen;
		this.logisticsCompany = logisticsCompany;
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
					logisticsCompany.register(new Journey(new Location(txtOrigin.getText()),
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



		txtOrigin = new JTextField();
		txtOrigin.setBounds(180, 105, 96, 20);
		txtOrigin.setColumns(10);

		JLabel lblOrigin = new JLabel("Origin:");
		lblOrigin.setBounds(140, 108, 40, 14);


		txtDestination = new JTextField();
		txtDestination.setBounds(180, 136, 96, 20);
		txtDestination.setColumns(10);

		JLabel lblDestination = new JLabel("Destination:");
		lblDestination.setBounds(110, 139, 70, 14);
		panelJourneyRegistration.add(lblDestination);

		txtCargo = new JTextField();
		txtCargo.setBounds(180, 167, 96, 20);
		txtCargo.setColumns(10);
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(140, 170, 40, 14);


		panelJourneyRegistration.add(btnBack);
		panelJourneyRegistration.add(btnRegister);
		panelJourneyRegistration.add(lblCargo);
		panelJourneyRegistration.add(txtCargo);
		panelJourneyRegistration.add(txtDestination);
		panelJourneyRegistration.add(lblOrigin);
		panelJourneyRegistration.add(txtOrigin);
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
