package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class LoginWindow extends JFrame {
	private String userName;
	private String password;
	private Color color;
	private JButton login = new JButton("login");
	private TextField userNameText = new TextField(20);
	private JPasswordField passwordText = new JPasswordField(20);
	
	public LoginWindow()
	{
		color = new Color(219, 245, 209);
		setTitle("Login");
		setSize(500, 200);
		JLabel welcome = new JLabel("Welcome to D2L, please login");
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
	
	public void addLoginListener(ActionListener listenForLoginButton) {
		login.addActionListener(listenForLoginButton);
	}
	
	public void updateUserNamePassword() { 
		userName = userNameText.getText(); 
		password = new String (passwordText.getPassword());
	}
	
	public void displayWrongLogin() {
		JOptionPane.showMessageDialog(null, "Wrong login Information.",
				"Error", JOptionPane.PLAIN_MESSAGE);
	}
	
	public String getUserName() {return userName;}
	public String getPassword() {return password;}
	
}
