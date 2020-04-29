package dk.dtu.gbar.gitlab.shipment.GUI;

import dk.dtu.gbar.gitlab.shipment.Client;
import dk.dtu.gbar.gitlab.shipment.ClientList;
import dk.dtu.gbar.gitlab.shipment.LogIn;

import java.awt.CardLayout;
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



public class LoginScreen
{
	SignUpScreen signUpScreen;
	MainMenuScreen mainMenuScreen;

	public JFrame frame;
	private JPanel panelMenu;
	private JButton btnLogin;
	private JButton btnSignUp;
	private LogIn login;

	public LoginScreen(LogIn login) {
		this.login = login;
	}
public LoginScreen() throws Exception{
		initialize();
		
	}

	private void initialize() {
		Client admin = new Client("Admin", "1234 Street st", "John Doe","example@email.com", "adminPass");
		ClientList clientList = new ClientList();
		clientList.add(admin);
		frame = new JFrame();
		frame.setBounds(100, 100, 475, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		panelMenu = new JPanel();
		frame.getContentPane().add(panelMenu, "name_160236068959176");
		panelMenu.setLayout(null);
		panelMenu.setBorder(BorderFactory.createTitledBorder("Login Menu"));
		
		
		JTextField txtLogin = new JTextField(20);
		JLabel lblLogin = new JLabel("Login:");
		
		JPasswordField password = new JPasswordField(20);
		JLabel lblPass = new JLabel("Password:");
		
		login = new LogIn(clientList);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(login.logIn(txtLogin.getText(), password.getText())) {
					setVisible(false);
					mainMenuScreen.setVisible(true);
				}
				
				
				
				
			}
		});
		
		btnSignUp = new JButton("SignUp");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				signUpScreen.setVisible(true);
			}
		});
		lblLogin.setBounds(104, 50, 130, 26);
		txtLogin.setBounds(150, 50, 130, 26);
		panelMenu.add(lblLogin);
		panelMenu.add(txtLogin);
		
		lblPass.setBounds(80,100,130,26);
		password.setBounds(150, 100, 130, 26);
		panelMenu.add(lblPass);
		panelMenu.add(password);
		
		btnLogin.setBounds(35,395,150,29);
		panelMenu.add(btnLogin);
		
		btnSignUp.setBounds(190,395,150,29);
		panelMenu.add(btnSignUp);
		
		mainMenuScreen = new MainMenuScreen(this);
		signUpScreen = new SignUpScreen(this);
		
	}
	
	public void setVisible(boolean aFlag) {
		panelMenu.setVisible(aFlag);
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void addPanel(JPanel panel) {
		frame.getContentPane().add(panel);
	}
}
