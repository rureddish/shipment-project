package dk.dtu.gbar.gitlab.shipment.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import dk.dtu.gbar.gitlab.shipment.Client;
import dk.dtu.gbar.gitlab.shipment.Journey;
import dk.dtu.gbar.gitlab.shipment.LogisticsCompany;

public class MainMenuScreen extends JFrame {

	private LoginScreen parentWindow;
	private LogisticsCompany logisticsCompany;
	private Client loggedIn;
	private JList listJourneys;
	private JPanel panelMainMenuFunctions;
	private JButton btnLogOut;
	private JRadioButton btnShowConcluded;
	private JRadioButton btnShowCurrent;
	private JRadioButton btnShowAll;
	private JButton btnSearch;
	private JButton btnExamine;
	private JButton btnRegisterJourney;
	
	
	///
	public MainMenuScreen(LoginScreen parentWindow, LogisticsCompany logisticsCompany, Client loggedIn) {
		this.parentWindow = parentWindow;
		this.logisticsCompany = logisticsCompany;
		this.loggedIn = loggedIn;
		initialize();
	}

	private void initialize() {
		panelMainMenuFunctions = new JPanel();
		parentWindow.addPanel(panelMainMenuFunctions);
		panelMainMenuFunctions.setLayout(null);
		panelMainMenuFunctions.setBorder(BorderFactory.createTitledBorder("Main Menu"));
		
		JTextField txtKeywordSearch = new JTextField(30);
		JLabel lblKeywordSearch = new JLabel("Keyword Search:");
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Checks what's in the txtKeywordSearch as well as if showConcluded and showCurrent are enabled
				//pulls up journeys based on keyword and showConcluded and showCurrent
			}
		});
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				parentWindow.setVisible(true);
			}
		});
		
		btnShowConcluded = new JRadioButton("Show Concluded");
		btnShowConcluded.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//shows concluded journeys based on keywords. Shows all concluded if keyword is blank
			}
		});
		
		btnShowCurrent = new JRadioButton("Show Current");
		btnShowCurrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//shows current journeys based on keywords. Shows all current if keyword is blank
			}
		});
		
		btnShowAll = new JRadioButton("Show All");
		btnShowAll.setLocation(18, 133);
		btnShowAll.setSize(105, 29);
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Shows all journeys based on keywords. Shows all if keyword is blank
			}
		});
		Journey[] clientJourneys = new Journey[loggedIn.getJourneys().size()];
		for(int i = 0; i < clientJourneys.length;i++){
				clientJourneys[i]=loggedIn.getJourneys().get(i);
		}
		listJourneys = new JList(clientJourneys);
		JScrollPane scrollJourneys = new JScrollPane(listJourneys);
		listJourneys.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listJourneys.setVisibleRowCount(5);
		scrollJourneys.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		listJourneys.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				//will theoretically be able to select any journey from the list to view details
			}
		});
		
		btnRegisterJourney = new JButton("Register Journey");
		btnRegisterJourney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		
		btnExamine = new JButton("Examine");
		btnExamine.setBounds(290, 378, 150, 29);
		btnExamine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String s = (String) listJourneys.getSelectedValue();
				System.out.println(s);
			}
		});
		
		
		txtKeywordSearch.setBounds(102,75, 130, 26);
		lblKeywordSearch.setBounds(21,75, 83, 26);
		
		btnShowConcluded.setBounds(18,108,129,29);
		btnShowCurrent.setBounds(149,108,105,29);
		btnLogOut.setBounds(290,11,150,29);
		btnSearch.setBounds(21,166,150,29);
		scrollJourneys.setSize(259, 214);
		scrollJourneys.setLocation(181, 153);
		
		


		
		panelMainMenuFunctions.add(lblKeywordSearch);
		panelMainMenuFunctions.add(txtKeywordSearch);
		panelMainMenuFunctions.add(btnShowConcluded);
		panelMainMenuFunctions.add(btnShowCurrent);
		panelMainMenuFunctions.add(btnShowAll);
		panelMainMenuFunctions.add(btnLogOut);
		panelMainMenuFunctions.add(btnSearch);
		panelMainMenuFunctions.add(scrollJourneys);
		panelMainMenuFunctions.add(btnExamine);
		panelMainMenuFunctions.add(btnRegisterJourney);
		
		
		
		
		
	}
	
	private void setEnableButtons(boolean enabled) {
		btnLogOut.setEnabled(enabled);
		btnShowConcluded.setEnabled(enabled);
		btnShowCurrent.setEnabled(enabled);

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
		panelMainMenuFunctions.setVisible(visible);
	}
	
	
}

