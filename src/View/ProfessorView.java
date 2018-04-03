package View;

import Data.Professor;

public class ProfessorView extends UserView{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4498875227121382873L;
	
	private CreateCourseView createCourseView = new CreateCourseView();
	private ProfessorCoursesPanel professorCoursesPanel = new ProfessorCoursesPanel();
	
	public CreateCourseView getCreateCourseView() {return createCourseView;}
	public ProfessorCoursesPanel getProfessorCoursesPanel() {return professorCoursesPanel;}
	
	public ProfessorView(Professor professor)
	{
		super(professor);
		
		tabbedPane.addTab("View Courses", null, professorCoursesPanel, null);
		tabbedPane.addTab("Create New Course", null, createCourseView, null);
	}
	
	public static void main(String[] args) {
		
		ProfessorView professorView = new ProfessorView(new Professor(1, "", "", 'P', "", "", ""));
	}
}
