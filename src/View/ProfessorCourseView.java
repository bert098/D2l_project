package View;

import Data.Course;
import java.util.ArrayList;

import Data.Assignment;

/**
 * Class used in the professor gui for displaying a course's information.
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg
 *
 */
public class ProfessorCourseView extends UserCourseView {
	
	/** serialVersionUID */
	private static final long serialVersionUID = -195895691490358230L;
	
	/** Panel for searching enrolled students. */
	private SearchStudentsPanel studentEnrolledSearchPanel = new SearchStudentsPanel();
	/** Panel for searching unenrolled students. */
	private SearchStudentsPanel searchUnenrolledStudentsPanel = new SearchStudentsPanel();
	/** Panel for searching the course's assignments. */
	private ProfessorAssignmentsPanel assignmentsPanel = new ProfessorAssignmentsPanel();
	
	/** @return studentEnrolledSearchPanel */
	public SearchStudentsPanel getSearchEnrolledStudentsPanel() {return studentEnrolledSearchPanel;}
	/** @return searchUnenrolledStudentsPanel */
	public SearchStudentsPanel getSearchAllStudentsPanel() {return searchUnenrolledStudentsPanel;}
	/** @return assignmentsPanel */
	public ProfessorAssignmentsPanel getProfessorAssignmentsPanel() {return assignmentsPanel;}
	
	/**
	 * Constructor for initializing a new ProfessorCourseView.
	 * @param course Set as the ProfessorCourseView's course. 
	 * @param userView Set as the ProfessorCourseView's userView.
	 */
	public ProfessorCourseView(Course course, ProfessorView userView)
	{
		super(course, userView);
		emailPanel = new UserEmailPanel("Send Email to Class");
		tabbedPane.addTab("Assignments", null, assignmentsPanel, null);
		tabbedPane.addTab("Send Email", null, emailPanel, null);
		tabbedPane.addTab("Search Enrolled Students", null, studentEnrolledSearchPanel, null);
		tabbedPane.addTab("Search Unenrolled Students", null, searchUnenrolledStudentsPanel, null);
		studentEnrolledSearchPanel.hideEnrollButton();
		searchUnenrolledStudentsPanel.hideUnenrollButton();
	}
	
	/**
	 * Displays the list of assignments in assignmentsPanel.
	 * @param assignmentList Used to update assignmentsPanel.
	 * @param id Used to update assignmentsPanel.
	 */
	public void displayAssignments(ArrayList<Assignment> assignmentList, Integer id) { 
		assignmentsPanel.displayAssignments(assignmentList, id); 
	}
	/** Sets the selected panel to studentEnrolledSearchPanel. */
	public void selectSearchStudentEnrolled()
	{
		tabbedPane.setSelectedIndex(2);
	}
	/** Sets the selected panel to searchUnenrolledStudentsPanel. */
	public void selectSearchStudentAll()
	{
		tabbedPane.setSelectedIndex(3);
	}
}
