package View;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Data.Student;

public class SearchStudentsPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7863274930372698496L;
	
	private ArrayList<Student> studentList;
	
	private ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField searchField = new JTextField();
	private JRadioButton idRadioButton = new JRadioButton("ID");
	private JRadioButton lastNameRadioButton = new JRadioButton("Last Name");
	private JButton searchButton = new JButton("Search");
	private JScrollPane scrollPane = new JScrollPane();
	private JList<Student> resultsList = new JList<Student>();
	private JButton unenrollButton = new JButton("Unenroll");
	private JButton enrollButton = new JButton("Enroll");
	
	public String getSearchText() {return searchField.getText();}
	
	public boolean idSelected() {return idRadioButton.isSelected();}
	public boolean lastNameSelected() {return lastNameRadioButton.isSelected();}
	
	public void addSearchButtonActionListener(ActionListener a) {searchButton.addActionListener(a);}
	public void addUnenrollButtonActionListener(ActionListener a) {unenrollButton.addActionListener(a);}
	public void addEnrollButtonActionListener(ActionListener a) {enrollButton.addActionListener(a);}
	
	public void setStudentList(ArrayList<Student> list) {studentList = list;}
	
	public SearchStudentsPanel()
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{132, 0, 176, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel searchStudentsLabel = new JLabel("Search Students");
		searchStudentsLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		GridBagConstraints gbc_searchStudentsLabel = new GridBagConstraints();
		gbc_searchStudentsLabel.anchor = GridBagConstraints.WEST;
		gbc_searchStudentsLabel.gridwidth = 2;
		gbc_searchStudentsLabel.insets = new Insets(0, 0, 5, 5);
		gbc_searchStudentsLabel.gridx = 0;
		gbc_searchStudentsLabel.gridy = 0;
		add(searchStudentsLabel, gbc_searchStudentsLabel);
		
		JLabel searchTypeLabel = new JLabel("Search Type:");
		GridBagConstraints gbc_searchTypeLabel = new GridBagConstraints();
		gbc_searchTypeLabel.anchor = GridBagConstraints.WEST;
		gbc_searchTypeLabel.insets = new Insets(0, 0, 5, 5);
		gbc_searchTypeLabel.gridx = 0;
		gbc_searchTypeLabel.gridy = 1;
		add(searchTypeLabel, gbc_searchTypeLabel);
		
		buttonGroup.add(idRadioButton);
		GridBagConstraints gbc_idRadioButton = new GridBagConstraints();
		gbc_idRadioButton.anchor = GridBagConstraints.WEST;
		gbc_idRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_idRadioButton.gridx = 0;
		gbc_idRadioButton.gridy = 2;
		add(idRadioButton, gbc_idRadioButton);
		
		buttonGroup.add(lastNameRadioButton);
		GridBagConstraints gbc_lastNameRadioButton = new GridBagConstraints();
		gbc_lastNameRadioButton.anchor = GridBagConstraints.WEST;
		gbc_lastNameRadioButton.insets = new Insets(0, 0, 5, 5);
		gbc_lastNameRadioButton.gridx = 0;
		gbc_lastNameRadioButton.gridy = 3;
		add(lastNameRadioButton, gbc_lastNameRadioButton);
		
		JLabel learchParameterLabel = new JLabel("Search Parameter:    ");
		GridBagConstraints gbc_learchParameterLabel = new GridBagConstraints();
		gbc_learchParameterLabel.insets = new Insets(0, 0, 5, 5);
		gbc_learchParameterLabel.anchor = GridBagConstraints.WEST;
		gbc_learchParameterLabel.gridx = 0;
		gbc_learchParameterLabel.gridy = 4;
		add(learchParameterLabel, gbc_learchParameterLabel);
		
		GridBagConstraints gbc_searchField = new GridBagConstraints();
		gbc_searchField.gridwidth = 2;
		gbc_searchField.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchField.insets = new Insets(0, 0, 5, 5);
		gbc_searchField.gridx = 0;
		gbc_searchField.gridy = 5;
		add(searchField, gbc_searchField);
		searchField.setColumns(20);
		
		GridBagConstraints gbc_searchButton = new GridBagConstraints();
		gbc_searchButton.anchor = GridBagConstraints.WEST;
		gbc_searchButton.insets = new Insets(0, 0, 5, 0);
		gbc_searchButton.gridx = 2;
		gbc_searchButton.gridy = 5;
		add(searchButton, gbc_searchButton);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 6;
		add(scrollPane, gbc_scrollPane);
		
		JLabel resultsLabel = new JLabel("Results:");
		scrollPane.setColumnHeaderView(resultsLabel);
		
		scrollPane.setViewportView(resultsList);
		
		GridBagConstraints gbc_enrollButton = new GridBagConstraints();
		gbc_enrollButton.insets = new Insets(0, 0, 0, 5);
		gbc_enrollButton.anchor = GridBagConstraints.WEST;
		gbc_enrollButton.gridx = 0;
		gbc_enrollButton.gridy = 7;
		add(enrollButton, gbc_enrollButton);
		
		GridBagConstraints gbc_unenrollButton = new GridBagConstraints();
		gbc_unenrollButton.anchor = GridBagConstraints.EAST;
		gbc_unenrollButton.gridx = 2;
		gbc_unenrollButton.gridy = 7;
		add(unenrollButton, gbc_unenrollButton);
	}
}
