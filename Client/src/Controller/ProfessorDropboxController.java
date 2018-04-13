package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import Data.Assignment;
import Data.Constants;
import Data.Dropbox;
import Model.ProfessorModel;
import View.ProfessorDropboxView;

/**
 * Class that connects the ProfessorModel and ProfessorDropboxView using action listeners.
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg	
 *
 */
public class ProfessorDropboxController implements Constants{

	/** dropboxView's assignment.  */
	private Assignment assignment; 
	/** The controller's ProfessorDropboxView. */
	private ProfessorDropboxView dropboxView;
	/** The controller's ProfessorModel. */
	private ProfessorModel professorModel;
	
	/**
	 * Constructor that initializes a new ProfessorDropboxController.
	 * @param dropboxView Assigned to dropboxView.
	 * @param model Assigned to professorModel.
	 * @param assignment Assigned to assignment.
	 */
	public ProfessorDropboxController(ProfessorDropboxView dropboxView, ProfessorModel model, Assignment assignment)
	{
		this.assignment = assignment; 
		this.dropboxView = dropboxView;
		professorModel = model; 
		displaySubmissions(); 
		addDropboxListeners();
	}
	
	/** Updates the submissions displayed in dropboxView. */
	public void displaySubmissions() { 
		professorModel.sendOperation(GET_SUBMISSIONS);
		professorModel.sendAssignmentId(assignment.getId());
		ArrayList<Dropbox> submissionList = professorModel.readSubmissionList();
		dropboxView.displaySubmissions(submissionList);
	}
	
	/** Adds the action listeners for the dropbox window. */
	private void addDropboxListeners()
	{
		/**
		 * Anonymous class for submit grade button. If the selected submission is not null uses professorModel to send the 
		 * grading information from dropboxView to the server. Otherwise does nothing.
		 */
		dropboxView.addSubmitGradeButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
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
		
		/**
		 * Anonymous class for download assignment button. Opens a file chooser to specify the location to download the file then calls
		 * downloadSubmission() from professorModel to download the file from the server.
		 */
		dropboxView.addDownloadAssignmentButtonActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Dropbox submission = dropboxView.getSelectedSubmission();
				
				JFileChooser fileBrowser = new JFileChooser(); 
				fileBrowser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				
				if (fileBrowser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
				{
					File file = fileBrowser.getCurrentDirectory();
					File currentDir = fileBrowser.getSelectedFile();
					String filePath = file.getAbsolutePath() + "\\" + currentDir.getName();
					professorModel.downloadSubmission(submission, filePath);
				}
			}
		});
	}

}
