package View;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * Class for creating a gui for sending emails.
 * @author Justin Hung, Robert Dumitru, Magnus Lyngberg
 *
 */
public class UserEmailPanel extends JPanel{

	/** serialVersionUID */
	private static final long serialVersionUID = -6727002174304116141L;
	
	/** Text field for the email's title. */
	private JTextField titleField = new JTextField();
	/** Scroll pane for holding messageArea */
	private JScrollPane scrollPane = new JScrollPane();
	/** Text area for the email's message. */
	private JTextArea messageArea = new JTextArea();
	/** Button for sending an email. */
	private JButton sendButton = new JButton("Send");
	/** Button for clearing the email's message and title. */
	private JButton clearButton = new JButton("Clear");
	
	/** @return The text in titleField. */
	public String getTitle() {return titleField.getText();}
	/** @return The text in messageArea */
	public String getMessage() {return messageArea.getText();}
	
	/**
	 * Adds an action listener for sendButton.
	 * @param a Action listener added to sendButton.
	 */
	public void addSendButtonActionListener(ActionListener a) {sendButton.addActionListener(a);}
	/**
	 * Adds an action listener for clearButton.
	 * @param a Action listener added to clearButton.
	 */
	public void addClearButtonActionListener(ActionListener a) {clearButton.addActionListener(a);}
	
	/** Clears messageArea and titleField. */
	public void clear()
	{
		messageArea.setText("");
		titleField.setText("");
	}
	
	/**
	 * Constructor for initializing a new UserEmailPanel.
	 * @param windowMessage Set to the window's label.
	 */
	public UserEmailPanel(String windowMessage)
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel sendEmailLabel = new JLabel(windowMessage);
		sendEmailLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		GridBagConstraints gbc_sendEmailLabel = new GridBagConstraints();
		gbc_sendEmailLabel.anchor = GridBagConstraints.WEST;
		gbc_sendEmailLabel.gridwidth = 2;
		gbc_sendEmailLabel.insets = new Insets(0, 0, 5, 5);
		gbc_sendEmailLabel.gridx = 0;
		gbc_sendEmailLabel.gridy = 0;
		add(sendEmailLabel, gbc_sendEmailLabel);
		
		JLabel titleLabel = new JLabel("Title:");
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.anchor = GridBagConstraints.WEST;
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 1;
		add(titleLabel, gbc_titleLabel);
		
		GridBagConstraints gbc_titleField = new GridBagConstraints();
		gbc_titleField.insets = new Insets(0, 0, 5, 0);
		gbc_titleField.gridwidth = 2;
		gbc_titleField.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleField.gridx = 0;
		gbc_titleField.gridy = 2;
		add(titleField, gbc_titleField);
		titleField.setColumns(10);
		
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridwidth = 2;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		JLabel messageLabel = new JLabel("Message:");
		scrollPane.setColumnHeaderView(messageLabel);
		
		messageArea.setLineWrap(true);
		scrollPane.setViewportView(messageArea);
		
		GridBagConstraints gbc_sendButton = new GridBagConstraints();
		gbc_sendButton.anchor = GridBagConstraints.WEST;
		gbc_sendButton.insets = new Insets(0, 0, 0, 5);
		gbc_sendButton.gridx = 0;
		gbc_sendButton.gridy = 4;
		add(sendButton, gbc_sendButton);
		
		GridBagConstraints gbc_clearButton = new GridBagConstraints();
		gbc_clearButton.anchor = GridBagConstraints.EAST;
		gbc_clearButton.gridx = 1;
		gbc_clearButton.gridy = 4;
		add(clearButton, gbc_clearButton);
	}
	
	/**
	 * Opens a dialogue prompting the user to enter the password for their email.
	 * @param emailAddress The user's email address.
	 * @return The user's password.
	 */
	public String getEmailPassword(String emailAddress)
	{
		JPasswordField passwordField = new JPasswordField(20);
		JComponent[] components = new JComponent[] {
		        new JLabel("Please enter password for: " + emailAddress),
		        passwordField};
		
		JOptionPane.showConfirmDialog(null, components,
				"Enter password", JOptionPane.PLAIN_MESSAGE);
		
		String password = new String(passwordField.getPassword());
		return password;
	}
}
