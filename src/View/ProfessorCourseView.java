package View;

import Data.Course;
import Data.Professor;

public class ProfessorCourseView extends UserCourseView {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -195895691490358230L;
	
	private SearchStudentsPanel studentSearchPanel = new SearchStudentsPanel();
	private ProfessorAssignmentsPanel assignmentsPanel = new ProfessorAssignmentsPanel();
	
	public SearchStudentsPanel getSearchStudentsPanel() {return studentSearchPanel;}
	public ProfessorAssignmentsPanel getProfessorAssignmentsPanel() {return assignmentsPanel;}
	
	public ProfessorCourseView(Course course)
	{
		super(course);
		tabbedPane.addTab("Search Students", null, studentSearchPanel, null);
		tabbedPane.addTab("Assignments", null, assignmentsPanel, null);
	}
	
	public static void main(String[] args)
	{
		ProfessorCourseView test = new ProfessorCourseView(new Course(new Professor(1, "", "", 'P', "", "", ""), 1, "", true));
	}
}
