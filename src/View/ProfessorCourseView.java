package View;

import Data.Course;
import java.util.ArrayList;

import Data.Assignment;

public class ProfessorCourseView extends UserCourseView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -195895691490358230L;
	
	private SearchStudentsPanel studentEnrolledSearchPanel = new SearchStudentsPanel();
	private SearchStudentsPanel searchAllStudentsPanel = new SearchStudentsPanel();
	private ProfessorAssignmentsPanel assignmentsPanel = new ProfessorAssignmentsPanel();
	
	public SearchStudentsPanel getSearchEnrolledStudentsPanel() {return studentEnrolledSearchPanel;}
	public SearchStudentsPanel getSearchAllStudentsPanel() {return searchAllStudentsPanel;}
	public ProfessorAssignmentsPanel getProfessorAssignmentsPanel() {return assignmentsPanel;}
	
	public ProfessorCourseView(Course course, ProfessorView userView)
	{
		super(course, userView);
		emailPanel = new UserEmailPanel("Send Email to Class");
		tabbedPane.addTab("Assignments", null, assignmentsPanel, null);
		tabbedPane.addTab("Send Email", null, emailPanel, null);
		tabbedPane.addTab("Search Enrolled Students", null, studentEnrolledSearchPanel, null);
		tabbedPane.addTab("Search all Students", null, searchAllStudentsPanel, null);
	}
	
	public void displayAssignments(ArrayList<Assignment> assignmentList, Integer id) { 
		assignmentsPanel.displayAssignments(assignmentList, id); 
	}
	public void selectSearchStudentEnrolled()
	{
		tabbedPane.setSelectedIndex(2);
	}
	public void selectSearchStudentAll()
	{
		tabbedPane.setSelectedIndex(3);
	}
}
