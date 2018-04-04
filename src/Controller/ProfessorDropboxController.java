package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import View.ProfessorDropboxView;

public class ProfessorDropboxController {
	
	private ProfessorDropboxView dropboxView;
	
	public ProfessorDropboxController(ProfessorDropboxView dropboxView)
	{
		this.dropboxView = dropboxView;
		addDropboxListeners();
	}
	
	private void addDropboxListeners()
	{
		dropboxView.addSubmitGradeButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Submit Grade");
			}
		});
		
		dropboxView.addDownloadAssignmentButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println("Download");
			}
		});
	}

}
