package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import Data.Constants;
import Model.ProfessorModel;
import View.ProfessorAssignmentsPanel;
import View.ProfessorCourseView;
import View.ProfessorDropboxView;
import View.SearchStudentsPanel;
import View.UserEmailPanel;
import Data.Assignment;

public class ProfessorCourseController implements Constants {
	
	private Integer courseId;
	private ProfessorModel professorModel; 
	private ProfessorCourseView courseView;
	
	public ProfessorCourseController(ProfessorCourseView courseView, ProfessorModel model, Integer id)
	{
		courseId = id;
		professorModel = model;
		this.courseView = courseView;
		displayAssignments(); 
		addProfessorCourseViewListeners();
	}
	
	private void addProfessorCourseViewListeners()
	{
		addAssignmentPanelListeners();
		addSearchStudentsPanelListeners();
		addEmailPanelListeners();
	}
	
	public void displayAssignments() { 
		professorModel.sendOperation(GET_ASSIGN);
		ArrayList<Assignment> assignmentList = professorModel.readAssignmentList(); 
		courseView.displayAssignments(assignmentList, courseId);
	}
	
	private void addAssignmentPanelListeners()
	{
		ProfessorAssignmentsPanel panel = courseView.getProfessorAssignmentsPanel();
		
		panel.addOpenDropboxButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Open Dropbox");
				courseView.setVisible(false);
				new ProfessorDropboxController(new ProfessorDropboxView(courseView));				
			}
		});
		
		panel.addUploadButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Upload");
				
				JFileChooser fileBrowser = new JFileChooser();
				
				if(fileBrowser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					File selectedFile = fileBrowser.getSelectedFile();
					
					long length = selectedFile.length();
					byte[] content = new byte[(int)length];
					
					try
					{
						FileInputStream fis = new FileInputStream(selectedFile);
						BufferedInputStream bos = new BufferedInputStream(fis);
						bos.read(content, 0, (int)length);
					}
					catch (FileNotFoundException e)
					{
						e.printStackTrace();
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					
					//Model(CONTENT)
				}
				
				
			}
		});
		
		panel.addActivateAssignButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Activate");
				professorModel.sendOperation(ACTIVATE_ASSIGN);
				Integer assignId = courseView.getProfessorAssignmentsPanel().getSelectedAssignment().getId();
				professorModel.sendDeactivateAssignment(assignId);
				System.out.println("odsifja");
				displayAssignments();
			}
		});
		
		panel.addDeactivateAssignButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Deactivate");
				professorModel.sendOperation(DEACTIVATE_ASSIGN);
				Integer assignId = courseView.getProfessorAssignmentsPanel().getSelectedAssignment().getId();
				professorModel.sendDeactivateAssignment(assignId);
				displayAssignments();
			}
		});
	}
	
	private void addSearchStudentsPanelListeners()
	{
		SearchStudentsPanel panel = courseView.getSearchStudentsPanel();
		
		panel.addSearchButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Search");
			}
		});
		
		panel.addEnrollButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Enroll");
			}
		});
		
		panel.addUnenrollButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Unenroll");
			}
		});
	}
	
	private void addEmailPanelListeners()
	{
		UserEmailPanel panel = courseView.getUserEmailPanel();
		
		panel.addSendButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Send");
			}
		});
		
		panel.addClearButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Clear");
			}
		});
	}
	
}
