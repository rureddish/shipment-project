package dk.dtu.gbar.gitlab.shipment.GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import M1.EntityList;
import M1.Entity;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;



public class LoginScreen extends JFrame
{

	SignUpScreen signUpScreen;
	EntityList<Entity> entityList;
	Entity entity;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					LoginScreen frame = new LoginScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginScreen()
	{
		
		initComponents();
		createEvents();
		
	}

	private void initComponents()
	{
		setTitle("Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(LoginScreen.class.getResource("/m4/resources/thanosMeme.jpg")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new CardLayout(0, 0));
		
		contentPane = new JPanel();
		getContentPane().add(contentPane, "name_160236068959176");
		contentPane.setBorder(BorderFactory.createTitledBorder(
                "Login"));
		contentPane.setLayout(null);

		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				checkLogin();
			}
		});
		btnLogin.setBounds(10, 225, 193, 29);
		contentPane.add(btnLogin);
		
		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
				signUpScreen.setVisible(true);
			}
		});
		btnSignUp.setBounds(232,225,193,29);
		contentPane.add(btnSignUp);
		
		textField = new JTextField();
		textField.setBounds(165, 63, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(102, 66, 53, 14);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(102, 102, 53, 14);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(165, 99, 96, 20);
		contentPane.add(passwordField);
		
		signUpScreen = new SignUpScreen(this);
		
	}
	private void createEvents()
	{

		
	}
	public boolean checkLogin()
	{
		setVisible(false);
		return true;

	}
	public void addPanel(JPanel panel) {
		getContentPane().add(panel);
	}
}
