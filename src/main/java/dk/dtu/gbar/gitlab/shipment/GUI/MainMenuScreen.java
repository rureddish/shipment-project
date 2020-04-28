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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MainMenuScreen {

	private LoginScreen parentWindow;
	private JList listJourneys;
	private JPanel panelMainMenuFunctions;
	private JButton btnLogOut;
	private JRadioButton btnShowConcluded;
	private JRadioButton btnShowCurrent;
	private JButton btnSearch;
	
	
	public MainMenuScreen(LoginScreen parentWindow) {
		this.parentWindow = parentWindow;
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
				//shows concluded journeys based on keywords
			}
		});
		
		btnShowCurrent = new JRadioButton("Show Current");
		btnShowCurrent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//shows current journeys based on keywords
			}
		});
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		listModel.addElement("This");
		listModel.addElement("Is");
		listModel.addElement("a");
		listModel.addElement("test");
		listModel.addElement("Will");
		listModel.addElement("eventually");
		listModel.addElement("be");
		listModel.addElement("done");
		listModel.addElement("via");
		listModel.addElement("journey");
		listModel.addElement("YEET");

		JScrollPane scrollJourneys = new JScrollPane(listJourneys);
		listJourneys = new JList<>(listModel);
		listJourneys.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				//will theoretically be able to select any journey from the list to view details
			}
		});
		
		
		txtKeywordSearch.setBounds(130,75, 130, 26);
		lblKeywordSearch.setBounds(21,75, 130, 26);
		
		btnShowConcluded.setBounds(21,100,150,29);
		btnShowCurrent.setBounds(170,100,150,29);
		btnLogOut.setBounds(21,28,150,29);
		btnSearch.setBounds(21,140,150,29);
		listJourneys.setBounds(200,150,150,200);
		
		listJourneys.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		panelMainMenuFunctions.add(lblKeywordSearch);
		panelMainMenuFunctions.add(txtKeywordSearch);
		panelMainMenuFunctions.add(btnShowConcluded);
		panelMainMenuFunctions.add(btnShowCurrent);
		panelMainMenuFunctions.add(btnLogOut);
		panelMainMenuFunctions.add(btnSearch);
		panelMainMenuFunctions.add(listJourneys);
		panelMainMenuFunctions.add(scrollJourneys);
		
		
		
		
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
