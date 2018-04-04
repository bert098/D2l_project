package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.ProfessorAssignmentsPanel;
import View.ProfessorCourseView;
import View.ProfessorDropboxView;
import View.SearchStudentsPanel;
import View.UserEmailPanel;

public class ProfessorCourseController {

	private ProfessorCourseView courseView;
	
	public ProfessorCourseController(ProfessorCourseView courseView)
	{
		this.courseView = courseView;
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
				new ProfessorDropboxController(new ProfessorDropboxView());				
			}
		});
		
		panel.addUploadButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Upload");
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
