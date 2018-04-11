package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Data.Assignment;
import Data.Constants;
import Data.Dropbox;
import Model.ProfessorModel;
import View.ProfessorDropboxView;

public class ProfessorDropboxController implements Constants{

	private Assignment assignment; 
	private ProfessorDropboxView dropboxView;
	private ProfessorModel professorModel;
	
	public ProfessorDropboxController(ProfessorDropboxView dropboxView, ProfessorModel model, Assignment assignment)
	{
		this.assignment = assignment; 
		this.dropboxView = dropboxView;
		professorModel = model; 
		displaySubmissions(); 
		addDropboxListeners();
	}
	
	public void displaySubmissions() { 
		professorModel.sendOperation(GET_SUBMISSIONS);
		professorModel.sendAssignmentId(assignment.getId());
		ArrayList<Dropbox> submissionList = professorModel.readSubmissionList();
		dropboxView.displaySubmissions(submissionList);
	}
	
	private void addDropboxListeners()
	{
		dropboxView.addSubmitGradeButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Submit Grade");
				Dropbox submission = dropboxView.getSelectedSubmission(); 
				if (submission == null) {
					return;
				}
				String comments = dropboxView.getCommentsText();
				String grade = dropboxView.getGradeText();
				if (grade.equals("")) {
					dropboxView.displayGradeError(); 
					return;
				}
				professorModel.sendOperation(GRADE_SUBMISSON); 
				professorModel.sendComments(comments); 
				professorModel.sendGrade(grade);
				professorModel.sendSubmissionId(submission.getId().toString());
				dropboxView.displayGradeSubmitted();
				displaySubmissions();
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
