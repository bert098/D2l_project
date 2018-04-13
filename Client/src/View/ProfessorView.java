package View;

import Data.Professor;

import java.util.ArrayList;

import Data.Course; 

/**
 * Class used in the professor's gui for after logging in.
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg
 *
 */
public class ProfessorView extends UserView{

	/** serialVersionUID */
	private static final long serialVersionUID = -4498875227121382873L;
	
	/** CreateCourseView used for creating new courses. */
	private CreateCourseView createCourseView = new CreateCourseView();
	/** ProfessorCoursesPanel used for viewing the professor's courses. */
	private ProfessorCoursesPanel professorCoursesPanel = new ProfessorCoursesPanel();
	
	/** @return createCourseView */
	public CreateCourseView getCreateCourseView() {return createCourseView;}
	/** @return professorCoursesPanel */
	public ProfessorCoursesPanel getProfessorCoursesPanel() {return professorCoursesPanel;}
	
	/**
	 * Constructor for initializing a new ProfessorView.
	 * @param professor Set to the view's user.
	 */
	public ProfessorView(Professor professor)
	{
		super(professor);
		
		tabbedPane.addTab("View Courses", null, professorCoursesPanel, null);
		tabbedPane.addTab("Create New Course", null, createCourseView, null);
	}
	
	/**
	 * Displays the professor's courses.
	 * @param courseList ArrayList containing the professor's courses.
	 */
	public void displayCourses(ArrayList<Course> courseList) {
		professorCoursesPanel.displayCourses(courseList);
		tabbedPane.setSelectedIndex(0);
	}
}
