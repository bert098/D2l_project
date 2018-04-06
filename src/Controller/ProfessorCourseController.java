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

import Data.Course;
import Data.Student;
import Model.ProfessorModel;
import View.ProfessorAssignmentsPanel;
import View.ProfessorCourseView;
import View.ProfessorDropboxView;
import View.SearchStudentsPanel;
import View.UserEmailPanel;

public class ProfessorCourseController {

	private ProfessorCourseView courseView;
	private ProfessorModel profModel;
	private SearchStudentsPanel searchStudent;
	public ProfessorCourseController(ProfessorCourseView courseView, ProfessorModel  profModel)
	{
		this.courseView = courseView;
		this.profModel = profModel;
		addProfessorCourseViewListeners();
	}
	
	private void addProfessorCourseViewListeners()
	{
		addAssignmentPanelListeners();
		addSearchStudentsPanelListeners();
		addEmailPanelListeners();
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
				// TODO Auto-generated method stub
				System.out.println("Activate");
			}
		});
		
		panel.addDeactivateAssignButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Deactivate");
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
				String s = panel.getSearchText();
				if(panel.idSelected()) 
				{
				Course c = courseView.getCourse();
				ArrayList<Student> a = profModel.SearchStudent(c);
				panel.displayStudentsId(a,s);
				}
				else if(panel.lastNameSelected())
				{
					Course c = courseView.getCourse();
					ArrayList<Student> a = profModel.SearchStudent(c);
					panel.displayStudentsName(a,s);
				}
				
				
			}
		});
		
		panel.addEnrollButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
			System.out.println("enroll");
			
			}
		});
		
		panel.addUnenrollButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Student s = panel.getSelectedStudent();
				ArrayList<Student> a  = profModel.unEnroll(s);
				panel.displayAll(a);
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
