package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Data.Professor;
import View.*;

public class ProfessorController {
	
	//private model ...

	private ProfessorView view;
	
	public ProfessorController(ProfessorView view)
	{
		this.view = view;
		addProfessorViewListeners();
	}
	
	
	private void addProfessorViewListeners()
	{
		addCreateCoursePanelListeners();
		addCoursesPanelListeners();
	}
	
	private void addCreateCoursePanelListeners()
	{
		CreateCourseView panel = view.getCreateCourseView();
		panel.addCreateCourseButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Create course");
			}
		});
	}
	
	private void addCoursesPanelListeners()
	{
		ProfessorCoursesPanel panel = view.getProfessorCoursesPanel();
		panel.addOpenCourseButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Open Course");
				new ProfessorCourseController(new ProfessorCourseView(panel.getSelectedCourse(), view));
			}
		});
		
		panel.addActivateCourseButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Activate");
			}
		});
		
		panel.addDeactivateCourseButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Deactivate");
			}
		});
	}
	
	public static void main(String[] args)
	{
		ProfessorView professorView = new ProfessorView(new Professor(1, "", "", 'P', "", "", ""));
		ProfessorController professorController = new ProfessorController(professorView);
	}

}
