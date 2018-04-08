package View;

import Data.Course;
import Data.Professor;

import java.util.ArrayList;

import Data.Assignment;

public class ProfessorCourseView extends UserCourseView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -195895691490358230L;
	
	
	private SearchStudentsPanel studentSearchPanel = new SearchStudentsPanel();
	private ProfessorAssignmentsPanel assignmentsPanel = new ProfessorAssignmentsPanel();
	
	public SearchStudentsPanel getSearchStudentsPanel() {return studentSearchPanel;}
	public ProfessorAssignmentsPanel getProfessorAssignmentsPanel() {return assignmentsPanel;}
	
	public ProfessorCourseView(Course course, ProfessorView userView)
	{
		super(course, userView);
		emailPanel = new UserEmailPanel("Send Email to Class");
		tabbedPane.addTab("Assignments", null, assignmentsPanel, null);
		tabbedPane.addTab("Send Email", null, emailPanel, null);
		tabbedPane.addTab("Search Students", null, studentSearchPanel, null);
	}
	
	public void displayAssignments(ArrayList<Assignment> assignmentList, Integer id) { 
		assignmentsPanel.displayAssignments(assignmentList, id); 
	}
}
