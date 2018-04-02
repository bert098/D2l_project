package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.TextField;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

public class LoginWindow extends JFrame {
	private String userName;
	private String password;
	private Color c = Color.GREEN;

	public LoginWindow()
	{
		setTitle("Login");
		setSize(500, 200);
		JLabel welcome = new JLabel("Welcome to D2L, please Login");
		JLabel usName = new JLabel("Username");
		JLabel pass = new JLabel("Password");
		TextField userNameText = new TextField(20);
		JPasswordField passwordText = new JPasswordField(20);
		JButton enter = new JButton("Enter");
		setLayout(new BorderLayout());
		JPanel subPanel = new JPanel();
		JPanel user = new JPanel();
		JPanel password = new JPanel();
		JPanel done = new JPanel();
		done.add(enter);
		done.setBackground(c);
		user.add(usName);
		user.add(userNameText);
		user.setBackground(c);
		password.add(pass);
		password.add(passwordText);
		password.setBackground(c);
		subPanel.setLayout(new BoxLayout(subPanel, BoxLayout.PAGE_AXIS));
		subPanel.add(user);
		subPanel.add(password);
		subPanel.setBackground(c);
		add(welcome, BorderLayout.NORTH);
		add(subPanel, BorderLayout.CENTER);
		add(done, BorderLayout.SOUTH);
		getContentPane().setBackground(c);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		LoginWindow l = new LoginWindow();
		
	}
	public String getUserName() {return userName;}
	public String getPassword() {return password;}
	
}
