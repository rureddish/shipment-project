package dk.dtu.gbar.gitlab.shipment.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SignUpScreen extends JFrame {

	private LoginScreen loginScreen;
	private JPanel signUpPanel;
	private JButton btnBack;
	private JButton btnRegister;

	
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
		
		JTextField txtUsername = new JTextField(20);
		JLabel lblUsername = new JLabel("Username:");
		JPasswordField password = new JPasswordField(20);
		JLabel lblPassword = new JLabel("Password:");
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				loginScreen.setVisible(true);
			}
		});
		
		btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Checks the values in username and password. If username is not in the database, adds username and password.
				//Otherwise, says username is already taken
			}
		});
		
		txtUsername.setBounds(160,100,130,29);
		lblUsername.setBounds(94,100,130,29);
		password.setBounds(160,150,130,29);
		lblPassword.setBounds(94,150,130,29);
		
		btnRegister.setBounds(120,350,150,29);
		btnBack.setBounds(21, 28, 74, 29);
		
		signUpPanel.add(lblPassword);
		signUpPanel.add(lblUsername);
		signUpPanel.add(txtUsername);
		signUpPanel.add(password);
		signUpPanel.add(btnRegister);
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
		btnRegister.setEnabled(enabled);
	}
	private void enableButtons() {
		setEnableButtons(true);
	}

	private void disableButtons() {
		setEnableButtons(false);
	}

}
