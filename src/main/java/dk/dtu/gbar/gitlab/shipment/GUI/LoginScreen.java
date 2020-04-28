package dk.dtu.gbar.gitlab.shipment.GUI;

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
		frame = new JFrame();
		frame.setBounds(100, 100, 404, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		panelMenu = new JPanel();
		frame.getContentPane().add(panelMenu, "name_160236068959176");
		panelMenu.setLayout(null);
		panelMenu.setBorder(BorderFactory.createTitledBorder("Login Menu"));
		
		
		JTextField txtLogin = new JTextField(20);
		JLabel lblLogin = new JLabel("Login:");
		
		JPasswordField pass = new JPasswordField(20);
		JLabel lblPass = new JLabel("Password:");
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*if(login.logIn(txtLogin.getText(), pass.getText())) {
					setVisible(false);
					mainMenuScreen.setVisible(true);
				}
				else {
					System.out.println("Incorrect Login/Password");
				}*/
				setVisible(false);
				mainMenuScreen.setVisible(true);
				
				
				
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
		pass.setBounds(150, 100, 130, 26);
		panelMenu.add(lblPass);
		panelMenu.add(pass);
		
		btnLogin.setBounds(35,395,150,29);
		panelMenu.add(btnLogin);
		
		btnSignUp.setBounds(190,395,150,29);
		panelMenu.add(btnSignUp);
		
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
