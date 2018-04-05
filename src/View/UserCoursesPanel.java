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

public abstract class UserCoursesPanel extends JPanel{
	
	protected ArrayList<Course> courses;
	
	protected DefaultListModel<Course> courseModel = new DefaultListModel<>(); 
	protected JList<Course> courseList = new JList<Course>();
	protected JScrollPane scrollPane = new JScrollPane();
	
	protected JButton openCourseButton = new JButton("Open");
	
	public void addOpenCourseButtonActionListener(ActionListener a) {openCourseButton.addActionListener(a);}

	public void setCourses(ArrayList<Course> courses) {this.courses = courses;}
	
	public Course getSelectedCourse() {return courseList.getSelectedValue();}
	
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
		gbc_openCourseButton.insets = new Insets(0, 0, 0, 5);
		gbc_openCourseButton.gridx = 0;
		gbc_openCourseButton.gridy = 2;
		add(openCourseButton, gbc_openCourseButton);	
	}
}
