package dk.dtu.gbar.gitlab.shipment.GUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dk.dtu.gbar.gitlab.shipment.Client;
import dk.dtu.gbar.gitlab.shipment.LogisticsCompany;

public class SignUpScreen extends JFrame {

	private LoginScreen loginScreen;
	private JPanel signUpPanel;
	private JButton btnBack;
	private JButton btnRegister;
	private LogisticsCompany logisticsCompany;

	
	public SignUpScreen(LoginScreen loginScreen, LogisticsCompany logisticsCompany) {
		this.loginScreen = loginScreen;
		this.logisticsCompany = logisticsCompany;
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
		JTextField txtEmail = new JTextField(20);
		txtEmail.setLocation(160, 123);
		txtEmail.setSize(130, 29);
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setLocation(107, 123);
		lblEmail.setSize(39, 29);
		JTextField txtRefPerson = new JTextField(20);
		txtRefPerson.setLocation(160, 83);
		txtRefPerson.setSize(130, 29);
		JLabel lblRefPerson = new JLabel("Reference Person:");
		lblRefPerson.setLocation(45, 83);
		lblRefPerson.setSize(113, 29);
		JTextField txtAddress = new JTextField(20);
		txtAddress.setSize(130, 29);
		txtAddress.setLocation(160, 203);
		JLabel lblAddress = new JLabel("Address:");
		lblAddress.setLocation(91, 203);
		lblAddress.setSize(67, 29);
		
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
				if(txtUsername.getText().isBlank() || password.getText().isBlank() || 
				txtEmail.getText().isBlank() || txtRefPerson.getText().isBlank() || 
				txtAddress.getText().isBlank()) {
					System.out.println("Please fill in all Spaces");
				}
				else {
					if(logisticsCompany.register(new Client(txtUsername.getText(),txtAddress.getText(),txtRefPerson.getText(),
					txtEmail.getText(), String.valueOf(password.getPassword())))) {
						System.out.println("Register Succesfull");
						txtUsername.setText("");
						password.setText("");
						txtEmail.setText("");
						txtRefPerson.setText("");
						txtAddress.setText("");
						setVisible(false);
						loginScreen.setVisible(true);
					}
					else {
						System.out.println("Email already in use.");
					}
					

					
				}
				
			}
		});
		
		txtUsername.setBounds(160,43,130,29);
		lblUsername.setBounds(83,43,74,29);
		password.setBounds(160,163,130,29);
		lblPassword.setBounds(84,163,74,29);
		
		btnRegister.setBounds(150,260,150,29);
		btnBack.setBounds(366, 11, 74, 29);
		
		signUpPanel.add(lblPassword);
		signUpPanel.add(lblUsername);
		signUpPanel.add(lblAddress);
		signUpPanel.add(lblRefPerson);
		signUpPanel.add(lblEmail);
		signUpPanel.add(txtUsername);
		signUpPanel.add(password);
		signUpPanel.add(txtAddress);
		signUpPanel.add(txtRefPerson);
		signUpPanel.add(txtEmail);
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
