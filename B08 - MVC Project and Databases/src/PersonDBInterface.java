
import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.text.JTextComponent;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileFilter;
import java.sql.SQLException;
import java.awt.Font;

/**
 * 
 * PersonDBInterface is the main GUI class for the Person Database program.
 * It creates a PersonDBManager object which is the layer that performs data
 * functions on the database. Under that layer is the database connection,
 * PersonDBConnect.
 * 
 * @author Ricardo Vasquez
 *
 */
public class PersonDBInterface {
	
	private PersonDBManager personDBM;
	private JFrame frmPersonDatabase;
	
	// Displays the connection status
	private JLabel statusBarLabel;
	
	// Container for the 4 main panels
	private JTabbedPane tabbedPane;
	
	// Panel to establish DB connections
	private JPanel connectPanel;
	private JLabel lblConnectTitle;
	private JTextField createDBTextbox;
	private JComboBox<String> existingDBComboBox;
	private JRadioButton rdbtnUseExistingDB;
	private JRadioButton rdbtnCreateDB;
	private ButtonGroup radioGroup;
	
	// Displays any connection errors
	private JLabel lblError;
	private JButton btnConnect;
	
	// Panel to add records to the table
	private JPanel addPanel;
	private JButton btnClearAll;
	private JButton btnAdd;
	private JTextField firstNameTextBox;
	private JTextField lastNameTextBox;
	private JTextField numberTextbox;
	private JTextField emailTextbox;
	private JCheckBox chckbxStudent;
	private JCheckBox chckbxEmployed;
	
	// Displays database insertion errors
	private JLabel lblAddError;
	
	// Shows all elements in the table
	private JPanel browsePanel;
	private JButton btnShowAll;
	private JTable browseTable;
	
	// Displays database query errors
	private JLabel lblBrowseError;
	
	// Deletes a given person from the DB
	private JPanel deletePanel;
	private JComboBox<String> nameComboBox;
	private JButton btnDelete;
	private JLabel lblDeleteError;
	
	// Displays row deletion errors
	private JLabel lblDeleteTitle;
	private JScrollPane scrollPane;
	
	/**
	 * 
	 * Create the EDT thread to display the GUI.
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Call the constructor for this class
					PersonDBInterface window = new PersonDBInterface();
					
					//Set it visible
					window.frmPersonDatabase.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public PersonDBInterface() {
		initialize();
	}
	
	/*
	 * Initializes the personDBManager object which establishes a connection to
	 * an existing database or a new database. Exceptions can be thrown by the
	 * inner methods, but will be caught and sent to the console and nearby
	 * label.
	 */
	public void getDBConnection() {
		
		// Holds the DB name
		String dbName = null;
		
		// Create or use existing DB
		if (rdbtnCreateDB.isSelected()) {
			dbName = createDBTextbox.getText();
		} else if (rdbtnUseExistingDB.isSelected()) {
			// Only get the substring before ".db"
			dbName = ((String) existingDBComboBox.getSelectedItem()).split(".db")[0];
		}
		
		try {
			
			// Establishes connection to the database
			personDBM = new PersonDBManager(dbName);
			
			lblError.setText("Connected Successfully!");
			System.out.println("Connected Successfully!");
			statusBarLabel.setText("Connected to " + dbName);
			
			// Allow user to use buttons
			toggleAllButtons(true);
			
		} catch (ClassNotFoundException cnfe) {
			
			// Display Error message and lock buttons
			lblError.setText("Connect Error - " + cnfe.getMessage());
			System.out.println("Connect Error - " + cnfe.getMessage());
			statusBarLabel.setText("Not Connected");
			toggleAllButtons(false);
		} catch (SQLException sqle) {
			
			// Display Error message and lock buttons
			lblError.setText("ERROR - " + sqle.getMessage());
			System.out.println("Connect Error - " + sqle.getMessage());
			statusBarLabel.setText("Not Connected");
			toggleAllButtons(false);
		}
	}
	
	/*
	 * Returns false if the text fields in the add panel are empty, and true if
	 * the are not empty.
	 */
	public boolean isValidInput() {
		
		boolean result = false;
		
		// All Fields need to be filled to be valid
		if ((!firstNameTextBox.getText().isEmpty() && !lastNameTextBox.getText().isEmpty()) 
				&& (!numberTextbox.getText().isEmpty() && !emailTextbox.getText().isEmpty())) {
			result = true;
		} else {
			lblAddError.setText("Input for this person is not valid. Please fill in all fields");
		}
		
		return result;
	}
	
	
	/*
	 * Toggles the enabled state of all the buttons, which can
	 * prevent the user from causing errors.
	 */
	public void toggleAllButtons(boolean state) {
		btnAdd.setEnabled(state);
		btnClearAll.setEnabled(state);
		btnShowAll.setEnabled(state);
		btnDelete.setEnabled(state);
	}
	
	
	/*
	 * Populates the combo box in the connect panel with filenames
	 * of database files in the user directory.
	 */
	public void populateExistingDBComboBox() {
		
		// Reset Items
		existingDBComboBox.removeAllItems();
		
		// Read the names of all files that end in ".db"
		File parent = new File(System.getProperty("user.dir"));
		File[] children = parent.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.getName().endsWith(".db");
			}
		});
		
		for (int i = 0; i < children.length; i++) {
			existingDBComboBox.insertItemAt(children[i].getName(), i);
		}
	}
	
	/*
	 * Populates the combo box in the delete panel with all the
	 * names of the people in the database. Combo box items are
	 * collected from the findAllPeople() ArrayList method in the
	 * PersonDBManager class.
	 */
	public void populateNameComboBox() {
		
		nameComboBox.removeAllItems();
		
		try {
			for (Person p : personDBM.findAllPeople()) {
				nameComboBox.addItem(p.getLastName() + ", " + p.getFirstName());
			}
		} catch (SQLException sqle) {
			lblDeleteError.setText("Delete Error - " + sqle.getMessage());
		}
		
		// Lock the Delete button if there's nothing to delete
		if (nameComboBox.getItemCount() == 0) {
			btnDelete.setEnabled(false);
		} else {
			btnDelete.setEnabled(true);
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		// Create the main Frame
		frmPersonDatabase = new JFrame();
		frmPersonDatabase.setTitle("Person Database");
		frmPersonDatabase.setBounds(100, 100, 497, 353);
		frmPersonDatabase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPersonDatabase.getContentPane().setLayout(null);
		
		// Create the Tabbed Panel over the main Frame
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 5, 495, 293);
		frmPersonDatabase.getContentPane().add(tabbedPane);
		
		// Create the Connect Panel
		connectPanel = new JPanel();
		tabbedPane.addTab("Connect", null, connectPanel, null);
		connectPanel.setLayout(null);
		
		// Create a Title label for the Connect Panel
		lblConnectTitle = new JLabel("Connect to a Person Database");
		lblConnectTitle.setBounds(109, 5, 217, 15);
		connectPanel.add(lblConnectTitle);
		
		// Add Radiobuttons on the Connect Panel
		rdbtnUseExistingDB = new JRadioButton("Use Existing DB");
		rdbtnUseExistingDB.setBounds(73, 92, 149, 23);
		rdbtnUseExistingDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existingDBComboBox.setEnabled(true);
				createDBTextbox.setEnabled(false);
				btnConnect.setEnabled(true);
			}
		});
		connectPanel.add(rdbtnUseExistingDB);
		
		rdbtnCreateDB = new JRadioButton("Create DB");
		rdbtnCreateDB.setBounds(73, 44, 149, 23);
		rdbtnCreateDB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				existingDBComboBox.setEnabled(false);
				createDBTextbox.setEnabled(true);
				btnConnect.setEnabled(true);
			}
		});
		connectPanel.add(rdbtnCreateDB);
		
		radioGroup = new ButtonGroup();
		radioGroup.add(rdbtnCreateDB);
		radioGroup.add(rdbtnUseExistingDB);
		
		// Add Textbox to Connect Panel
		createDBTextbox = new JTextField();
		createDBTextbox.setBounds(246, 49, 177, 19);
		createDBTextbox.setEnabled(false);
		connectPanel.add(createDBTextbox);
		createDBTextbox.setColumns(10);
		
		// Add Combobox to Connect Panel
		existingDBComboBox = new JComboBox<String>();
		existingDBComboBox.setBounds(246, 92, 177, 24);
		existingDBComboBox.setEnabled(false);
		// Populate Combobox with Existing Databases
		populateExistingDBComboBox();
		connectPanel.add(existingDBComboBox);
		
		// Add Connect Button to the Connect Panel 
		btnConnect = new JButton("Connect");
		btnConnect.setBounds(306, 144, 117, 25);
		btnConnect.setEnabled(false);
		btnConnect.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if (personDBM != null) {
				
					try {
						// Close the connection and open a new one
						personDBM.getConnection().closeConnection();
						personDBM = null;
						getDBConnection();
					} catch (SQLException sqle) {
						lblError.setText("Error disconnecting from DB - " + sqle.getMessage());
					}
				} else {
					getDBConnection();
				}
				
				populateExistingDBComboBox();
				populateNameComboBox();
			}
		});
		connectPanel.add(btnConnect);
		
		// Add a error label to the connect panel
		lblError = new JLabel("");
		lblError.setFont(new Font("Dialog", Font.BOLD, 10));
		lblError.setBounds(67, 181, 356, 45);
		connectPanel.add(lblError);
		
		// Add status label to the bottom of the connect panel
		statusBarLabel = new JLabel("Not Connected");
		statusBarLabel.setBounds(0, 303, 428, 21);
		frmPersonDatabase.getContentPane().add(statusBarLabel);
		
		// Add the Add Person Panel to the Tabbed Panel
		addPanel = new JPanel();
		tabbedPane.addTab("Add", null, addPanel, null);
		addPanel.setLayout(null);
		JLabel lblSubtitle = new JLabel("Please enter a Person's information.");
		lblSubtitle.setBounds(106, 14, 314, 15);
		addPanel.add(lblSubtitle);
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(48, 41, 100, 15);
		addPanel.add(lblFirstName);
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setBounds(48, 101, 70, 15);
		addPanel.add(lblNumber);
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(48, 134, 70, 15);
		addPanel.add(lblEmail);
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(48, 74, 100, 15);
		addPanel.add(lblLastName);
		chckbxStudent = new JCheckBox("Student");
		chckbxStudent.setBounds(254, 165, 89, 23);
		addPanel.add(chckbxStudent);
		chckbxEmployed = new JCheckBox("Employed");
		chckbxEmployed.setBounds(150, 165, 100, 23);
		addPanel.add(chckbxEmployed);
		btnClearAll = new JButton("Clear All");
		btnClearAll.setBounds(202, 229, 117, 25);
		btnClearAll.addActionListener(new ActionListener() {
			/*
			 * Clears all the text fields and checkboxes.
			 */
			public void actionPerformed(ActionEvent e) {
				for (Component C : addPanel.getComponents()) {
					if (C instanceof JTextField) {
						((JTextComponent) C).setText("");
					}
					if (C instanceof JCheckBox) {
						((JCheckBox) C).setSelected(false);
					}
				}
			}
		});
		
		addPanel.add(btnClearAll);
		btnAdd = new JButton("Add");
		btnAdd.setBounds(342, 229, 117, 25);
		btnAdd.addActionListener(new ActionListener() {
			/*
			 * Creates a Person object from the text fields and
			 * check boxes only if they aren't empty. Then the fields
			 * are cleared and the nameComboBox in the delete panel is
			 * cleared and populated.
			 */
			public void actionPerformed(ActionEvent e) {
				if (isValidInput()) {
					try {
						personDBM.addPersonToDB(new Person(
								personDBM.generatePersonID(),
								firstNameTextBox.getText(),
								lastNameTextBox.getText(),
								numberTextbox.getText(),
								emailTextbox.getText(),
								chckbxStudent.isSelected(),
								chckbxEmployed.isSelected()));
						lblAddError.setText(
								"Person successfully added to Database.");
						browseTable.removeAll();
						populateNameComboBox();
						for (Component C : addPanel.getComponents()) {
							if (C instanceof JTextField) {
								((JTextComponent) C).setText("");
							}
							if (C instanceof JCheckBox) {
								((JCheckBox) C).setSelected(false);
							}
						}
					} catch (SQLException sqle) {
						lblAddError.setText(sqle.getMessage());
					}
				}
			}
		});
		addPanel.add(btnAdd);
		firstNameTextBox = new JTextField();
		firstNameTextBox.setBounds(150, 39, 309, 19);
		addPanel.add(firstNameTextBox);
		firstNameTextBox.setColumns(10);
		lastNameTextBox = new JTextField();
		lastNameTextBox.setColumns(10);
		lastNameTextBox.setBounds(150, 72, 309, 19);
		addPanel.add(lastNameTextBox);
		numberTextbox = new JTextField();
		numberTextbox.setBounds(150, 101, 309, 19);
		addPanel.add(numberTextbox);
		numberTextbox.setColumns(10);
		emailTextbox = new JTextField();
		emailTextbox.setBounds(150, 132, 309, 19);
		addPanel.add(emailTextbox);
		emailTextbox.setColumns(10);
		lblAddError = new JLabel("");
		lblAddError.setFont(new Font("Dialog", Font.BOLD, 10));
		lblAddError.setBounds(48, 186, 411, 36);
		addPanel.add(lblAddError);
		
		// Creates a Delete Panel to the Tabbed Panel
		deletePanel = new JPanel();
		tabbedPane.addTab("Delete", null, deletePanel, null);
		deletePanel.setLayout(null);
		nameComboBox = new JComboBox<String>();
		nameComboBox.setBounds(27, 79, 246, 24);
		deletePanel.add(nameComboBox);
		lblDeleteError = new JLabel("");
		lblDeleteError.setFont(new Font("Dialog", Font.BOLD, 10));
		lblDeleteError.setBounds(26, 160, 427, 40);
		deletePanel.add(lblDeleteError);
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(27, 123, 117, 25);
		btnDelete.addActionListener(new ActionListener() {
			/*
			 * Splits the item string in the name Combo box into first
			 * and last name so they can be used as arguments to
			 * delete a person from the database. The deletePerson()
			 * returns true if it successfully deletes the person.
			 */
			public void actionPerformed(ActionEvent e) {
				String firstName =
						((String) nameComboBox.getSelectedItem()).split(", ")[1];
				String lastName =
						((String) nameComboBox.getSelectedItem()).split(", ")[0];
				try {
					if (personDBM.deletePerson(firstName, lastName)) {
						browseTable.removeAll();
						populateNameComboBox();
						lblDeleteError.setText(firstName
								+ "'s record has been deleted successfully.");
					}
				} catch (SQLException sqle) {
					lblDeleteError.setText("Delete Error - "
							+ sqle.getMessage());
					System.out.println("Delete Error - " +
							sqle.getMessage());
				}
			}
		});
		deletePanel.add(btnDelete);
		lblDeleteTitle = new JLabel(
				"Select for a Person's name to delete.");
		lblDeleteTitle.setBounds(26, 41, 324, 15);
		deletePanel.add(lblDeleteTitle);
		
		// Add a Browse Panel to the Tabed Panel
		browsePanel = new JPanel();
		tabbedPane.addTab("Browse", null, browsePanel, null);
		browsePanel.setLayout(null);
		btnShowAll = new JButton("Show All Persons");
		btnShowAll.addActionListener(new ActionListener() {
			/*
			 * Fills the table in the browse panel with all the
			 * elements in the database. The PersonDBManager's
			 * buildTableModel returns a DefaultTableModel which is
			 * accepted by the setModel() method to fill the table.
			 */
			public void actionPerformed(ActionEvent e) {
				try {
					browseTable.setModel(personDBM.buildTableModel());
				} catch (SQLException sqle) {
					lblBrowseError.setText("Error - "
							+ sqle.getMessage());
					System.out.println("Error - "
							+ sqle.getMessage());
				}
			}
		});
		btnShowAll.setBounds(12, 27, 169, 25);
		browsePanel.add(btnShowAll);
		toggleAllButtons(false);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 64, 466, 162);
		browsePanel.add(scrollPane);
		browseTable = new JTable();
		browseTable.setFillsViewportHeight(true);
		browseTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(browseTable);
		browseTable.setBorder(new BevelBorder(BevelBorder.LOWERED,null, null, null, null));
		lblBrowseError = new JLabel("");
		lblBrowseError.setBounds(22, 229, 401, 25);
		browsePanel.add(lblBrowseError);
	}
}
