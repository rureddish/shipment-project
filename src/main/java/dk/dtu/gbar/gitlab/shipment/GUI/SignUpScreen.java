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

public class SignUpScreen extends JFrame {

	private LoginScreen loginScreen;
	private JPanel signUpPanel;
	private JButton btnBack;

	
	public SignUpScreen(LoginScreen loginScreen) {
		this.loginScreen = loginScreen;
		initialize();
	}


	public void initialize()
	{
		signUpPanel = new JPanel();
		loginScreen.addPanel(signUpPanel);
		signUpPanel.setLayout(null);
		signUpPanel.setBorder(BorderFactory.createTitledBorder("Sign Up"));
		
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				loginScreen.setVisible(true);
			}
		});
		btnBack.setBounds(21, 28, 74, 29);
		signUpPanel.add(btnBack);

		disableButtons();
		
	}
	public void setVisible(boolean visible) {
		if (!visible) {
			disableButtons();
		}
		else {
			enableButtons();
		}
		signUpPanel.setVisible(visible);
	}
	
	public void setEnableButtons(boolean enabled) {
		btnBack.setEnabled(enabled);
	}
	private void enableButtons() {
		setEnableButtons(true);
	}

	private void disableButtons() {
		setEnableButtons(false);
	}

}
