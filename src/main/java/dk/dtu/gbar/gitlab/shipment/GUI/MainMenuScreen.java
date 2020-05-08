package dk.dtu.gbar.gitlab.shipment.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

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
import javax.swing.table.DefaultTableModel;

import dk.dtu.gbar.gitlab.shipment.Client;
import dk.dtu.gbar.gitlab.shipment.Journey;
import dk.dtu.gbar.gitlab.shipment.LogIn;
import dk.dtu.gbar.gitlab.shipment.LogisticsCompany;
import dk.dtu.gbar.gitlab.shipment.Searcher;

import javax.swing.JTable;

public class MainMenuScreen extends JFrame implements PropertyChangeListener {

	private LoginScreen parentWindow;
	private JourneyRegisterScreen journeyRegisterScreen;
	private LogisticsCompany logisticsCompany;
	private LogIn loggedIn;
	private JPanel panelMainMenuFunctions;
	private JButton btnLogOut;
	private JRadioButton btnShowConcluded;
	private JRadioButton btnShowCurrent;
	private JRadioButton btnShowAll;
	private JButton btnSearch;
	private JButton btnExamine;
	private JButton btnRegisterJourney;
	private JTable tblJourneys;
	private DefaultTableModel clientJourneys;
	private Searcher search;
	private String keyword;
	private ArrayList<Journey> journeys;
	
	
	///
	public MainMenuScreen(LoginScreen parentWindow, LogisticsCompany logisticsCompany, LogIn loggedIn) {
		this.parentWindow = parentWindow;
		this.logisticsCompany = logisticsCompany;
		this.loggedIn = loggedIn;
		search = new Searcher(logisticsCompany);
		journeys = loggedIn.getLoggedInClient().getJourneys();
		keyword = "";
		
		logisticsCompany.addObserver(this);
		
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
				keyword = txtKeywordSearch.getText();
				ArrayList searchResults = search.journeySearchByString(journeys, keyword);
				clientJourneys.setRowCount(0);
				display(searchResults);
				//Checks what's in the txtKeywordSearch as well as if showConcluded and showCurrent are enabled
				//pulls up journeys based on keyword and showConcluded and showCurrent
			}
		});
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.setLocation(290, 11);
		btnLogOut.setSize(150, 29);
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				parentWindow.setVisible(true);
			}
		});
		
		btnShowConcluded = new JRadioButton("Show Concluded");
		btnShowConcluded.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnShowCurrent.setSelected(false);
				btnShowAll.setSelected(false);
				clientJourneys.setRowCount(0);
				journeys = search.getConcludedJourneys(loggedIn.getLoggedInClient());
				ArrayList searchResults = search.journeySearchByString(journeys, keyword);
				display(searchResults);
			}
		});
		
		btnShowCurrent = new JRadioButton("Show Current");
		btnShowCurrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnShowConcluded.setSelected(false);
				btnShowAll.setSelected(false);
				clientJourneys.setRowCount(0);
				journeys = search.getCurrentJourneys(loggedIn.getLoggedInClient());
				ArrayList searchResults = search.journeySearchByString(journeys, keyword);
				display(searchResults);
			}
		});
		
		btnShowAll = new JRadioButton("Show All");
		btnShowAll.setLocation(18, 133);
		btnShowAll.setSize(77, 29);
		btnShowAll.setSelected(true);
		btnShowAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnShowConcluded.setSelected(false);
				btnShowCurrent.setSelected(false);
				clientJourneys.setRowCount(0);
				journeys = loggedIn.getLoggedInClient().getJourneys();
				ArrayList searchResults = search.journeySearchByString(journeys, keyword);
				display(searchResults);				
			}
		});
		
		clientJourneys = new DefaultTableModel();
		clientJourneys.addColumn("Origin");
		clientJourneys.addColumn("Destination");
		clientJourneys.addColumn("Cargo");
		for(Journey journey: loggedIn.getLoggedInClient().getJourneys()) {
			clientJourneys.addRow(new Object[] {journey.getOrigin().getPlaceName(),	journey.getDestination().getPlaceName(),journey.getCargo()});
		}

		
		JScrollPane scrollJourneys = new JScrollPane();
		scrollJourneys.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		tblJourneys = new JTable(clientJourneys);
		scrollJourneys.setViewportView(tblJourneys);
		
		
		btnRegisterJourney = new JButton("Register New Journey");
		btnRegisterJourney.setLocation(21, 35);
		btnRegisterJourney.setSize(173, 29);
		btnRegisterJourney.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				journeyRegisterScreen.setVisible(true);
			}
		});
		
		
		btnExamine = new JButton("Examine");
		btnExamine.setBounds(290, 378, 150, 29);
		btnExamine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		txtKeywordSearch.setBounds(102,75, 130, 26);
		lblKeywordSearch.setBounds(21,75, 83, 26);
		btnShowConcluded.setBounds(18,108,129,29);		
		btnShowCurrent.setBounds(149,108,105,29);
		btnSearch.setBounds(290,74,150,29);
		scrollJourneys.setSize(338, 214);
		scrollJourneys.setLocation(102, 153);
		
		
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
		
		
		journeyRegisterScreen = new JourneyRegisterScreen(parentWindow,this, loggedIn, logisticsCompany);
		
		
	}
	public void addJourney(Journey journey) {
		clientJourneys.addRow(new Object[] {journey.getOrigin().getPlaceName(),journey.getDestination().getPlaceName(),
				journey.getCargo()});
	}


	
	private void setEnableButtons(boolean enabled) {
		btnLogOut.setEnabled(enabled);
		btnShowConcluded.setEnabled(enabled);
		btnShowCurrent.setEnabled(enabled);
		btnShowConcluded.setEnabled(enabled);
		btnShowAll.setEnabled(enabled);
		btnRegisterJourney.setEnabled(enabled);


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
	
	private void display(ArrayList<Journey> searchResults) {
		for(Journey journey: searchResults) {
			clientJourneys.addRow(new Object[] {journey.getOrigin().getPlaceName(),	journey.getDestination().getPlaceName(),journey.getCargo()});
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		initialize();
		
	}
	
}

