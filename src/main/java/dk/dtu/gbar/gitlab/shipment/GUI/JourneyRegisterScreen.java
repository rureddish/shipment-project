package dk.dtu.gbar.gitlab.shipment.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import dk.dtu.gbar.gitlab.shipment.Client;
import dk.dtu.gbar.gitlab.shipment.Journey;
import dk.dtu.gbar.gitlab.shipment.Location;
import dk.dtu.gbar.gitlab.shipment.LogIn;
import dk.dtu.gbar.gitlab.shipment.LogisticsCompany;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JLabel;
import javax.swing.JList;

public class JourneyRegisterScreen extends JFrame {

	private JPanel panelJourneyRegistration;
	private LoginScreen parentWindow;
	private MainMenuScreen mainMenuScreen;
	private LogIn loggedIn;
	private JButton btnRegister;
	private JButton btnBack;
	private JList lstOrigin;
	private JList lstDestination;
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

		
		
		DefaultListModel<String> ports = new DefaultListModel<>();
		for(int i = 0; i < logisticsCompany.getLocationList().size(); i++) {
			ports.addElement(logisticsCompany.getLocationList().get(i).getPlaceName());
		}
		lstOrigin = new JList<>(ports);
		lstOrigin.setBounds(100,30,80,60);
		lstOrigin.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		lstOrigin.setVisibleRowCount(2);
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(10, 260, 150, 29);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtCargo.getText().isBlank()) {
					System.out.println("Please Fill Out All Spaces");
				}
				else {
					
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

		
		
		JScrollPane scrollDestination = new JScrollPane();
		scrollDestination.setLocation(180, 143);
		scrollDestination.setSize(96, 50);
		JScrollPane scrollOrigin = new JScrollPane(lstOrigin);
		scrollOrigin.setLocation(180, 73);
		scrollOrigin.setSize(96, 50);
		

		JLabel lblOrigin = new JLabel("Origin:");
		lblOrigin.setBounds(130, 93, 40, 14);
		JLabel lblDestination = new JLabel("Destination:");
		lblDestination.setBounds(104, 161, 70, 14);
		

		txtCargo = new JTextField();
		txtCargo.setBounds(180, 207, 96, 20);
		txtCargo.setColumns(10);
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(130, 210, 40, 14);


		panelJourneyRegistration.add(btnBack);
		panelJourneyRegistration.add(btnRegister);
		panelJourneyRegistration.add(lblCargo);
		panelJourneyRegistration.add(txtCargo);
		panelJourneyRegistration.add(lblDestination);
		panelJourneyRegistration.add(lblOrigin);

		panelJourneyRegistration.add(scrollDestination);
		lstDestination = new JList<>(ports);
		scrollDestination.setViewportView(lstDestination);
		panelJourneyRegistration.add(scrollOrigin);
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
