package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import Model.ProfessorModel;
import View.*;
import Data.Course;
import Data.Constants;

/**
 * Class that connects ProfessorModel and ProfessorView elements together. 
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg	
 *
 */
public class ProfessorController implements Constants{
	/** Model used for communicating with the server. */
	private ProfessorModel professorModel;
	/** ProfessorView that provides the gui for the client. */
	private ProfessorView view;
	/** List of courses used by both the model and the view. */
	private ArrayList<Course> courseList;
	
	/**
	 * Constructor that initializes a new view using an existing model and and view.
	 * @param view Assigned to view.
	 * @param model Assigned to professorModel.
	 */
	public ProfessorController(ProfessorView view, ProfessorModel model) 
	{
		professorModel = model;
		this.view = view;
		addCourses();
		addProfessorViewListeners();
	}
	
	/** Updates the courses displayed in view. */
	public void addCourses() {
		professorModel.sendOperation(PROF_COURSES);
		courseList = professorModel.readCourseList(); 
		view.displayCourses(courseList);
	}
	
	/** Adds all of the action listeners for view. */
	private void addProfessorViewListeners()
	{
		addCreateCoursePanelListeners();
		addCoursesPanelListeners();
		addCloseWindowListener();
	}
	
	/** Adds the action listeners for view's CreateCourseView panel using anonymous classes. */
	private void addCreateCoursePanelListeners()
	{
		CreateCourseView panel = view.getCreateCourseView();
		/**
		 * Anonymous class for creating new course button. Creates a new Course from the information in panel and
		 * calls createCourse() from the professor model. Then updates the courses in view.
		 */
		panel.addCreateCourseButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {				
				Course newCourse = new Course(panel.getCourseNum(),view.getUserId(), panel.getCourseName(), panel.getCourseIsActive());
				professorModel.createCourse(newCourse);
				addCourses();
			}
		});
	}
	/** Adds the action listeners for view's ProfessorCoursesPanel panel using anonymous classes. */
	private void addCoursesPanelListeners()
	{
		ProfessorCoursesPanel panel = view.getProfessorCoursesPanel();
		/**
		 * Anonymous class for open course button. If a course is selected in panel, hides view and creates a new ProfessorCourseController
		 * and ProfessorCourseView. Does nothing otherwise.
		 */
		panel.addOpenCourseButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(panel.getSelectedCourse() != null) {
					view.setVisible(false);
					new ProfessorCourseController(new ProfessorCourseView(panel.getSelectedCourse(), view),
							professorModel, panel.getSelectedCourse().getId());
				}
			}
		});
		
		/**
		 * Anonymous class for activate course button. If a course is selected calls activateCourse() from professorModel. Does nothing
		 * otherwise.
		 */
		panel.addActivateCourseButtonActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (panel.getSelectedCourse() == null)
				{
					return;
				}
				professorModel.activateCourse(panel.getSelectedCourse().getId());
				addCourses();
			}
		});
		
		/**
		 * Anonymous class for deactivate course button. If a course is selected calls deactivateCourse() from professorModel. Does nothing
		 * otherwise.
		 */
		panel.addDeactivateCourseButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (panel.getSelectedCourse() == null)
				{
					return;
				}
				professorModel.deactivateCourse(panel.getSelectedCourse().getId());
				addCourses();
			}
		});
	}
	
	/** Adds the action listener for closing view using an anonymous class. */
	private void addCloseWindowListener() {
		view.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
				   professorModel.sendOperation("EXIT");
			   }
		});
	}
}
