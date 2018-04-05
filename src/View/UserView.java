package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Data.User;

public abstract class UserView extends JFrame{
	
	protected User user;

	protected JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	protected UserCourseView userCourseView;
	
	public UserCourseView getUserCourseView() {return userCourseView;}
	
	public Integer getUserId() {return user.getId();}
	
	public UserView(User user)
	{
		this.user = user;
		
		setVisible(true);
		
		setSize(500, 500);
		setMinimumSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel mainLabel = new JLabel("D4L");
		mainLabel.setFont(new Font("Tahoma", Font.PLAIN, 32));
		panel.add(mainLabel);
		
		JLabel userLabel = new JLabel(user.getFirstName() + " " + user.getLastName());
		panel.add(userLabel);
		
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
	}
}
