package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

/**
 * Class used that creates a gui used to login to a server.
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg
 *
 */
public class LoginWindow extends JFrame {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -3166276186130521165L;
	
	/** User's username. */
	private String userName;
	/** User's password. */
	private String password;
	/** The color of the window. */
	private Color color;
	/** Button used to login. */
	private JButton login = new JButton("login");
	/** Text field for inputting the username. */
	private TextField userNameText = new TextField(20);
	/** Used for inputting the password. */
	private JPasswordField passwordText = new JPasswordField(20);
	
	/**
	 * Constructor for initializing a new LoginWindow.
	 */
	public LoginWindow()
	{
		color = new Color(219, 245, 209);
		setTitle("Login");
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		JLabel welcome = new JLabel("Desire 4 Learning", SwingConstants.CENTER);
		welcome.setFont(new Font("Sans Serif", Font.BOLD, 30));
		JLabel usName = new JLabel("Username");
		JLabel pass = new JLabel("Password");
		setLayout(new BorderLayout());
		JPanel subPanel = new JPanel();
		JPanel user = new JPanel();
		JPanel password = new JPanel();
		JPanel done = new JPanel();
		done.add(login);
		done.setBackground(color);
		user.add(usName);
		user.add(userNameText);
		user.setBackground(color);
		password.add(pass);
		password.add(passwordText);
		password.setBackground(color);
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.PAGE_AXIS));
		subPanel.add(user);
		subPanel.add(password);
		subPanel.setBackground(color);
		add(welcome, BorderLayout.NORTH);
		add(subPanel, BorderLayout.CENTER);
		add(done, BorderLayout.SOUTH);
		getContentPane().setBackground(color);
		setVisible(true);
	}
	
	/**
	 * Adds an action listener for login.
	 * @param listenForLoginButton Action listener added to assignDetailsButton.
	 */
	public void addLoginListener(ActionListener listenForLoginButton) {
		login.addActionListener(listenForLoginButton);
	}
	
	/** Updates the values of userName and password. */
	public void updateUserNamePassword() { 
		userName = userNameText.getText(); 
		password = new String (passwordText.getPassword());
	}
	
	/**	Displays a dialogue message if the username and password are incorrect. */
	public void displayWrongLogin() {
		JOptionPane.showMessageDialog(null, "Wrong login Information.",
				"Error", JOptionPane.PLAIN_MESSAGE);
	}
	
	/** @return userName */
	public String getUserName() {return userName;}
	/** @return password */
	public String getPassword() {return password;}
	
	/** Hides the login window. */
	public void closeWindow() {this.setVisible(false);}
	
}
