package dk.dtu.gbar.gitlab.shipment.GUI;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class LoginScreen
{
	SignUpScreen signUpScreen;
	MainMenuScreen mainMenuScreen;

	public JFrame frame;
	private JPanel panelMenu;
	private JButton btnLogin;
	private JButton btnSignUp;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
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
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
