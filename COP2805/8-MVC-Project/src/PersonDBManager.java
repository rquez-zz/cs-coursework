import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * PersonDBManager contains various methods which update or collect
 * data from the database. It uses a PersonDBConnect object which
 * creates the connection to the database to query and update the
 * database. All methods pass exceptions to the GUI layer to be caught.
 * 
 * @author Ricardo Vasquez
 *
 */
public class PersonDBManager {
	
	// Database layer object
	private PersonDBConnect connection;
	
	
	/**
	 * 
	 * Main constructor for this class. 
	 * Relays exceptions up to the GUI layer 
	 * 
	 * @param dbName: Name of the database to connect to
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public PersonDBManager(String dbName) throws SQLException, ClassNotFoundException {
		
		/*
		 * This object is used throughout the class to make queries and updates
		 * to the database
		 */
		connection = new PersonDBConnect(dbName);
		
		// People table is created only if it doesn't exist.
		connection.createTableUnique("People",
				"(ID  INT PRIMARY KEY NOT NULL, "
						+ "First_Name  TEXT NOT NULL, "
						+ "Last_Name TEXT NOT NULL, "
						+ "Number TEXT NOT NULL, "
						+ "Email TEXT NOT NULL, "
						+ "Student  INT NOT NULL, "
						+ "Employed INT NOT NULL)");
	}
	
	public PersonDBConnect getConnection() {
		return connection;
	}
	
	public void setConnection(PersonDBConnect connection) {
		this.connection = connection;
	}
	
	/**
	 * 
	 * Returns an integer that is one increment greater than the greatest
	 * ID value in the database. Since ID is a primary key, all ID values
	 * have to be unique.
	 * 
	 * @return Unique ID in the database
	 * @throws SQLException
	 */
	public int generatePersonID() throws SQLException {
		
		int genID = 0;
		
		ResultSet rs = connection.queryDatabase("SELECT ID FROM People ORDER BY ID DESC");
		
		if (rs.next()) {
			genID = rs.getInt(1) + 1;
		}
		
		return genID;
	}
	
	/**
	 * 
	 * Adds a person object to the database.
	 * Since sqlite can't store boolean values, they are
	 * converted to integer before being added to the database.
	 * 
	 * @param person: The person object with user inputed data
	 * @throws SQLException
	 */
	public void addPersonToDB(Person person) throws SQLException {
		
		int studentBooleanValue = (person.isStudent()) ? 1 : 0;
		int employedBooleanValue = (person.isEmployed()) ? 1 : 0;
		
		String sqlString =
				"INSERT INTO People (ID, First_Name, Last_Name, "
						+ "Number, Email, Student, Employed) VALUES ("
						+ "'"
						+ person.getId()
						+ "', '"
						+ person.getFirstName()
						+ "', '"
						+ person.getLastName()
						+ "', '"
						+ person.getNumber()
						+ "', '"
						+ person.getEmail()
						+ "', "
						+ studentBooleanValue + ", " + employedBooleanValue + ")";

		connection.updateDatabase(sqlString);
	}
	

	/**
	 * 
	 * Returns a person object that corresponds to an ID 
	 * in the database. 
	 * 
	 * @param personID: The ID field for a person in the database
	 * @return A person object constructed using database content
	 * @throws SQLException
	 */
	public Person getPerson(int personID) throws SQLException {
		
		String query = "SELECT * FROM People WHERE ID = " + personID;
		
		ResultSet rs = connection.queryDatabase(query);
		
		// Initialize
		String firstName = "";
		String lastName = "";
		String number = "";
		String email = "";
		
		boolean isStudent = false;
		boolean isEmployed = false;
		
		while (rs.next()) {
			firstName = rs.getString(2);
			lastName = rs.getString(3);
			number = rs.getString(4);
			email = rs.getString(5);
			isStudent = (rs.getInt(6) == 1);
			isEmployed = (rs.getInt(7) == 1);
		}
		
		return new Person(personID, firstName, lastName, number, email, isStudent, isEmployed);
	}
	
	/**
	 * 
	 * Returns an ArrayList of all the Person objects in the 
	 * database. 
	 * 
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Person> findAllPeople() throws SQLException {
		
		String query = "SELECT * FROM People";
		
		ResultSet set = connection.queryDatabase(query);
		
		ArrayList<Person> resultArrayList = new ArrayList<Person>();
		
		// Fill the ArrayList
		while (set.next()) {
			resultArrayList.add(getPerson(set.getInt(1)));
		}
		
		return resultArrayList;
	}
	
	/**
	 * 
	 * Deletes a Person object from the database by looking
	 * up the first and last name and then dropping by 
	 * person ID
	 * 
	 * @param firstName: Person object's firstName attribute
	 * @param lastName: Person object's lastName attribute
	 * @return T if deletion was successful
	 * @throws SQLException
	 */
	public boolean deletePerson(String firstName, String lastName) throws SQLException {
		
		boolean successful = false;
		
		String query = "SELECT ID FROM People WHERE First_Name = '" + firstName + "' AND Last_Name = '" + lastName + "'";
		
		ResultSet rs = connection.queryDatabase(query);
		
		// Initialize
		int personID = 0;
		
		// Get the Person ID
		while (rs.next()) {
			personID = rs.getInt(1);
		}
		
		//Stores the Person object before it gets deleted.
		Person deletedPerson = getPerson(personID);
		connection.updateDatabase("DELETE FROM People WHERE ID = " + personID);
		
		/*
		 * If the deleted person is not in the findAllPeople()
		 * ArrayList then the deletion was successful.
		 */
		if (!findAllPeople().contains(getPerson(personID))) {
			successful = true;
			System.out.println("Recored Deleted: " + deletedPerson.toString());
		} else {
			System.out.println("Error Deleting Person ID: " + personID);
			successful = false; 
		}
		return successful;
	}
	
	/**
	 * 
	 * Creates a TableModel object to fill the JTable in the GUI
	 * 
	 * @return DefaultTableModel object with all the rows of the database
	 * @throws SQLException
	 */
	public DefaultTableModel buildTableModel() throws SQLException {
		
		// Execute the Query 
		ResultSet rs = connection.queryDatabase("SELECT * FROM People");
		
		/*
		 * The ResultSetMetaData object gets the column properties
		 * and types for ResultSet object that it wraps. 
		 */
		ResultSetMetaData metaData = rs.getMetaData();
		
		/*
		 * DefaultTableModel doesn't take an
		 * ArrayList. That's why vectors are 
		 * used. 
		 *
		 */
		 Vector<String> columnNames = new Vector<String>();
				
		int columnCount = metaData.getColumnCount();
		
		// Add the column names to the columnNames object 
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
		}
		
		// Table Data 
		Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			data.add(vector);
		}
		
		return new DefaultTableModel(data, columnNames);
	}
}
