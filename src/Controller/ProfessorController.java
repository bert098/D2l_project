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
import View.*;
import Data.Course;
import Data.Constants;

public class ProfessorController implements Constants{
	
	private BufferedReader stringIn;
	private PrintWriter stringOut;
	private ObjectOutputStream objectOut;
	private ObjectInputStream objectIn;
	private ProfessorView view;
	private ArrayList<Course> courseList;
	
	public ProfessorController(ProfessorView view, PrintWriter out, BufferedReader in, ObjectOutputStream oOut, ObjectInputStream oIn )
	{
		stringOut = out ;
		stringIn = in; 
		objectOut = oOut;
		objectIn = oIn;
		this.view = view;
		addCourses();
		addProfessorViewListeners();
	}
	
	public void addCourses() {
		try {
			System.out.println("test");
			stringOut.println(PROF_COURSES);
			courseList = (ArrayList<Course>) objectIn.readObject(); 
			for (int i = 0; i < courseList.size(); i ++) 
			{
				System.out.println(courseList.get(i).toString());
			}
			view.displayCourses(courseList);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	
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
}
