package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;

import Data.Professor;
import Model.ProfessorModel;
import View.*;
import Data.Course;
import Data.Constants;

public class ProfessorController implements Constants{
	private ProfessorModel professorModel;
	private ProfessorView view;
	private ArrayList<Course> courseList;
	
	public ProfessorController(ProfessorView view, ProfessorModel model) 
	{
		professorModel = model;
		this.view = view;
		addCourses();
		addProfessorViewListeners();
	}
	
	public void addCourses() {
		professorModel.sendOperation(PROF_COURSES);
		courseList = professorModel.readCourseList(); 
		view.displayCourses(courseList);
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
				
				Course newCourse = new Course(panel.getCourseNum(),view.getUserId(), panel.getCourseName(), panel.getCourseIsActive());
				professorModel.createCourse(newCourse);
				addCourses();
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
				view.setVisible(false);
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
}
