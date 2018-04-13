package View;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Data.Course;

/**
 * Abstract class used for displaying a user's courses.
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg
 *
 */
public abstract class UserCoursesPanel extends JPanel{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -7700320768345519179L;
	
	/** Model for courseList. */
	protected DefaultListModel<Course> courseModel = new DefaultListModel<>();
	/** List for displaying a user's courses. */
	protected JList<Course> courseList = new JList<Course>();
	/** Scroll pane for courseList */
	protected JScrollPane scrollPane = new JScrollPane();
	/** Button for opening a course's view. */
	protected JButton openCourseButton = new JButton("Open");
	
	/**
	 * Adds an action listener for openCourseButton.
	 * @param a Action listener added to openCourseButton.
	 */
	public void addOpenCourseButtonActionListener(ActionListener a) {openCourseButton.addActionListener(a);}
	
	/** @return The currently selected course in courseList. */
	public Course getSelectedCourse() {return courseList.getSelectedValue();}
	
	/**
	 * Constructor used to initialize a new UserCoursesPanel.
	 */
	public UserCoursesPanel()
	{
		courseList.setModel(courseModel);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 276, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel coursesLabel = new JLabel("Courses");
		coursesLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
		GridBagConstraints gbc_coursesLabel = new GridBagConstraints();
		gbc_coursesLabel.gridwidth = 3;
		gbc_coursesLabel.anchor = GridBagConstraints.WEST;
		gbc_coursesLabel.insets = new Insets(0, 0, 5, 0);
		gbc_coursesLabel.gridx = 0;
		gbc_coursesLabel.gridy = 0;
		add(coursesLabel, gbc_coursesLabel);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		add(scrollPane, gbc_scrollPane);
		
		scrollPane.setViewportView(courseList);
		
		GridBagConstraints gbc_openCourseButton = new GridBagConstraints();
		gbc_openCourseButton.anchor = GridBagConstraints.WEST;
		gbc_openCourseButton.insets = new Insets(0, 0, 0, 5);
		gbc_openCourseButton.gridx = 0;
		gbc_openCourseButton.gridy = 2;
		add(openCourseButton, gbc_openCourseButton);	
	}
	
	/**
	 * Displays the courses in the ArrayList.
	 * @param courseArrayList Used to update courseModel.
	 */
	public void displayCourses(ArrayList<Course> courseArrayList)
	{
		courseModel.removeAllElements();
		for (int i = 0; i < courseArrayList.size(); i++)
		{
			courseModel.addElement(courseArrayList.get(i));
		}
		setVisible(true);
	}
}
