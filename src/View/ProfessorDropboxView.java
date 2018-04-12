package View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Data.Assignment;
import Data.Dropbox;

/**
 * Class used in the professor's gui for displaying student submissions for an assignment.
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg
 *
 */
public class ProfessorDropboxView extends JFrame{
	
	/** serialVersionUID */
	private static final long serialVersionUID = 8673038957037574746L;
	
	/** The view that is used to open this view. */
	private ProfessorCourseView courseView;
	
	/** Panel for holding the view's elements. */
	private JPanel contentPane;
	/** Text field for inputting a submission's grade. */
	private JTextField gradeField = new JTextField();
	/** Scroll pane for holding gradeField. */
	private JScrollPane scrollPane = new JScrollPane();
	/** List model for submissionsList. */
	private DefaultListModel<Dropbox> submissionModel = new DefaultListModel<>();
	/** List for holding all of the submissions. */
	private JList<Dropbox> submissionsList = new JList<Dropbox>();
	/** Text area for inputting comments for a submission. */
	private JTextArea commentsArea = new JTextArea();
	/** Button for grading a submission. */
	private JButton submitGradeButton = new JButton("Submit Grade");
	/** Button for downloading a student's submission. */
	private JButton downloadAssignmentButton = new JButton("Download");
	
	/** Returns the contents of gradeField */
	public String getGradeText() {return gradeField.getText();}
	/** Returns the contents of commentsArea */
	public String getCommentsText() {return commentsArea.getText();}
	
	/**
	 * Adds an action listener for submitGradeButton.
	 * @param a Action listener added to submitGradeButton.
	 */
	public void addSubmitGradeButtonActionListener(ActionListener a) {submitGradeButton.addActionListener(a);}
	/**
	 * Adds an action listener for downloadAssignmentButton.
	 * @param a Action listener added to downloadAssignmentButton.
	 */
	public void addDownloadAssignmentButtonActionListener(ActionListener a) {downloadAssignmentButton.addActionListener(a);}
	
	/**
	 * Constructor for initializing a new ProfessorDropboxView.
	 * @param assignment The dropbox's assignment.
	 * @param courseView Set to courseView.
	 */
	public ProfessorDropboxView(Assignment assignment, ProfessorCourseView courseView)
	{
		this.courseView = courseView;
		
		submissionsList.setModel(submissionModel);
		setSize(500, 500);
		setMinimumSize(new Dimension(500, 500));
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel dropboxLabel = new JLabel("Dropbox");
		dropboxLabel.setFont(new Font("SansSerif", Font.PLAIN, 24));
		GridBagConstraints gbc_dropboxLabel = new GridBagConstraints();
		gbc_dropboxLabel.gridwidth = 2;
		gbc_dropboxLabel.anchor = GridBagConstraints.WEST;
		gbc_dropboxLabel.insets = new Insets(0, 0, 5, 5);
		gbc_dropboxLabel.gridx = 0;
		gbc_dropboxLabel.gridy = 0;
		contentPane.add(dropboxLabel, gbc_dropboxLabel);
		
		JLabel assignmentLabel = new JLabel(assignment.getTitle());
		assignmentLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		GridBagConstraints gbc_assignmentLabel = new GridBagConstraints();
		gbc_assignmentLabel.gridwidth = 2;
		gbc_assignmentLabel.anchor = GridBagConstraints.WEST;
		gbc_assignmentLabel.insets = new Insets(0, 0, 5, 5);
		gbc_assignmentLabel.gridx = 0;
		gbc_assignmentLabel.gridy = 1;
		contentPane.add(assignmentLabel, gbc_assignmentLabel);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		scrollPane.setViewportView(submissionsList);
		
		JLabel commentsLabel = new JLabel("Comments:");
		GridBagConstraints gbc_commentsLabel = new GridBagConstraints();
		gbc_commentsLabel.anchor = GridBagConstraints.WEST;
		gbc_commentsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_commentsLabel.gridx = 0;
		gbc_commentsLabel.gridy = 3;
		contentPane.add(commentsLabel, gbc_commentsLabel);
		
		commentsArea.setLineWrap(true);
		commentsArea.setRows(3);
		GridBagConstraints gbc_commentsArea = new GridBagConstraints();
		gbc_commentsArea.gridheight = 2;
		gbc_commentsArea.gridwidth = 3;
		gbc_commentsArea.insets = new Insets(0, 0, 5, 5);
		gbc_commentsArea.fill = GridBagConstraints.BOTH;
		gbc_commentsArea.gridx = 0;
		gbc_commentsArea.gridy = 4;
		contentPane.add(commentsArea, gbc_commentsArea);
		
		GridBagConstraints gbc_submitGradeButton = new GridBagConstraints();
		gbc_submitGradeButton.anchor = GridBagConstraints.WEST;
		gbc_submitGradeButton.insets = new Insets(0, 0, 0, 5);
		gbc_submitGradeButton.gridx = 0;
		gbc_submitGradeButton.gridy = 6;
		contentPane.add(submitGradeButton, gbc_submitGradeButton);
		
		GridBagConstraints gbc_gradeField = new GridBagConstraints();
		gbc_gradeField.anchor = GridBagConstraints.WEST;
		gbc_gradeField.insets = new Insets(0, 0, 0, 5);
		gbc_gradeField.gridx = 1;
		gbc_gradeField.gridy = 6;
		contentPane.add(gradeField, gbc_gradeField);
		gradeField.setColumns(4);
		
		GridBagConstraints gbc_downloadAssignmentButton = new GridBagConstraints();
		gbc_downloadAssignmentButton.gridx = 2;
		gbc_downloadAssignmentButton.gridy = 6;
		contentPane.add(downloadAssignmentButton, gbc_downloadAssignmentButton);
		
		this.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
				   closeWindow();
			   }
		});
		
		setVisible(true);
	}
	
	/** Closes the window. */
	private void closeWindow()
	{
		courseView.setVisible(true);
		this.dispose();
	}
	
	/**
	 * Updates the contents of submissionModel.
	 * @param submissionArrayList Used to update submissionModel.
	 */
	public void displaySubmissions(ArrayList<Dropbox> submissionArrayList) {
		submissionModel.removeAllElements();
		for (int i = 0; i < submissionArrayList.size(); i++) {
			submissionModel.addElement(submissionArrayList.get(i));
		}
		setVisible(true);
	}
	
	/** Displays a dialogue if the inputed grade is incorrect. */
	public void displayGradeError() {
		JOptionPane.showMessageDialog(null, "Please enter a grade before submitting",
				"Error", JOptionPane.PLAIN_MESSAGE);
	}
	
	/** Displays a dialogue indicating that the submission's grade has been updated. */
	public void displayGradeSubmitted() {
		JOptionPane.showMessageDialog(null, "Grade submitted successfully",
				"", JOptionPane.PLAIN_MESSAGE);
	}
	
	/** Returns the selected submission. */
	public Dropbox getSelectedSubmission() { 
		return submissionsList.getSelectedValue();
	}
}
