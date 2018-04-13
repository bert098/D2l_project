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

/**
 * Abstract class used for displaying information about a user's course.
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg
 *
 */
public abstract class UserCourseView extends JFrame{
	

	/** serialVersionUID */
	private static final long serialVersionUID = 5093225887456340253L;
	/** The UserView that is used to open this view. */
	protected UserView userView;
	/** Tabbed-pane for holding panels. */
	protected JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	/** The course that is being viewed. */
	protected Course course;
	/** Panel that allows a user to send emails. */
	protected UserEmailPanel emailPanel;
	
	/** @return emailPanel */
	public UserEmailPanel getUserEmailPanel() {return emailPanel;}
	
	/** Disables this window. */
	public void deactivateWindow() {this.setEnabled(false);}
	/** Enables this window. */
	public void activateWindow() {this.setEnabled(true);}
	
	/** @return The user's email. */
	public String getEmail() {return userView.getUserEmail();}
	
	/**
	 * Constructor for initializing a new UserCourseView.
	 * @param course Set to course.
	 * @param userView Set to userView
	 */
	protected UserCourseView(Course course, UserView userView)
	{
		this.userView = userView;
		this.course = course;
		
		setSize(565, 500);
		setMinimumSize(new Dimension(565, 500));
		setLocationRelativeTo(null);
		
		JLabel courseLabel = new JLabel(course.getName() + " " + course.getId());
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
	
	/** Method for closing the window. */
	private void closeWindow()
	{
		userView.setVisible(true);
		this.dispose();
	}
	/** @return course. */
	public Course getCourse() {return course;}
	/** @return userView */
	public UserView getView() {return userView;}
}
