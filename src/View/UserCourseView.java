package View;

import Data.Assignment;
import Data.Course;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;


public abstract class UserCourseView extends JFrame{
	
	protected UserView userView;
	
	protected JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	protected Course course;
	
	protected UserEmailPanel emailPanel = new UserEmailPanel();
	
//	public UserAssignmentsPanel getUserAssignmentsPanel() {return assignmentsPanel;}
	public UserEmailPanel getUserEmailPanel() {return emailPanel;}
	
	public void deactivateWindow() {this.setEnabled(false);}
	public void activateWindow() {this.setEnabled(true);}
	
	protected UserCourseView(Course course, UserView userView)
	{
		this.userView = userView;
		this.course = course;
		
		setSize(500, 500);
		setMinimumSize(new Dimension(500, 500));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel courseLabel = new JLabel("courseName");
		courseLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
		getContentPane().add(courseLabel, BorderLayout.NORTH);
		
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		tabbedPane.addTab("Send Email", null, emailPanel, null);
		
		setVisible(true);
	}
}
