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
	
	private JPanel panelMainMenuFunctions;
	private JButton btnLogOut;
	private JRadioButton btnShowConcluded;
	private JRadioButton btnShowCurrent;
	
	
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
		
		
		
		txtKeywordSearch.setBounds(130,75, 130, 26);
		lblKeywordSearch.setBounds(21,75, 130, 26);
		
		btnShowConcluded.setBounds(21,100,150,29);
		btnShowCurrent.setBounds(170,100,150,29);
		btnLogOut.setBounds(21,28,150,29);
		
		panelMainMenuFunctions.add(lblKeywordSearch);
		panelMainMenuFunctions.add(txtKeywordSearch);
		panelMainMenuFunctions.add(btnShowConcluded);
		panelMainMenuFunctions.add(btnShowCurrent);
		panelMainMenuFunctions.add(btnLogOut);

		
		
		
		
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
