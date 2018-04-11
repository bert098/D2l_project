package View;

import Data.Course;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;


public abstract class UserCourseView extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5093225887456340253L;

	protected UserView userView;
	
	protected JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	
	protected Course course;
	
	protected UserEmailPanel emailPanel;
	
	public UserEmailPanel getUserEmailPanel() {return emailPanel;}
	
	public void deactivateWindow() {this.setEnabled(false);}
	public void activateWindow() {this.setEnabled(true);}
	
	protected UserCourseView(Course course, UserView userView)
	{
		this.userView = userView;
		this.course = course;
		
		setSize(500, 500);
		setMinimumSize(new Dimension(500, 500));
		setLocationRelativeTo(null);
		
		JLabel courseLabel = new JLabel(course.getName());
		courseLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
		getContentPane().add(courseLabel, BorderLayout.NORTH);
		
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		this.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
				   closeWindow();
			   }
		});
		
		setVisible(true);
	}
	
	private void closeWindow()
	{
		userView.setVisible(true);
		this.dispose();
	}
	public Course getCourse()
	{
		return course;
	}
	public UserView getView()
	{
		return userView;
	}
}
