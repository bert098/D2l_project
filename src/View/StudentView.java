package View;

import java.util.ArrayList;

import Data.Course;
import Data.Student;

/**
 * Class used in the student's gui after a student logs in.
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg
 *
 */
public class StudentView extends UserView{
	
	/** serialVersionUID */
	private static final long serialVersionUID = -5373923137481277482L;
	
	/** Panel for displaying a student's courses. */
	private StudentCoursesPanel studentCoursesPanel = new StudentCoursesPanel();
	/** @return studentCoursesPanel */
	public StudentCoursesPanel getStudentCoursesPanel() {return studentCoursesPanel;}
	
	/** Constructor for intializing a new StudentView. */
	public StudentView(Student student) {
		
		super(student);
		tabbedPane.addTab("View Courses", null, studentCoursesPanel, null);
	}
	
	/**
	 * Displays the student's courses in studentCoursesPanel.
	 * @param courseList ArrayList containing a student's courses.
	 */
	public void displayCourses(ArrayList<Course> courseList) {
		studentCoursesPanel.displayCourses(courseList);
	}
}
