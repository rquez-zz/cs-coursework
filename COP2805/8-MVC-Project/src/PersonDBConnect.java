import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Ricardo Vasquez
 * 
 * PersonDBConnect creates the connection between the middle layer
 * and the database. All exceptions are passed to the middle layer
 * and then passed to the GUI where they are caught.
 */

public class PersonDBConnect {
	
	private Connection connection;
	
	
	/**
	 * @param dbName: Name of the database to connect to
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * 
	 * Constructor for this class.
	 */
	public PersonDBConnect(String dbName) throws ClassNotFoundException, SQLException {
		
		/*
		 * SQLite .jar file should be a library added to the build path
		 */
		Class.forName("org.sqlite.JDBC");
		connection = DriverManager.getConnection("jdbc:sqlite:" + dbName + ".db");
	}
	
	/**
	 * Queries the database using the queryString and returns
	 * a ResultSet object.
	 * 
	 * @param queryString: String with the SQL query 
	 * @return rs: The ResultSet object with the query results
	 * @throws SQLException
	 */
	public ResultSet queryDatabase(String queryString) throws SQLException {
		
		Statement statement = connection.createStatement();
		ResultSet rs = statement.executeQuery(queryString);
		return rs;
	}
	
	/**
	 * 
	 * Creates a table that is unique to the database. 
	 * 
	 * @param tableName: Name of the table to be created
	 * @param tableFields: Comma separated string of fields for the table
	 * @throws SQLException
	 */
	public void createTableUnique(String tableName, String tableFields) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + tableName + tableFields);
	}
	
	/**
	 * 
	 * Updates the database. 
	 * Called by a PersonDBManager object to add or delete a person.
	 * 
	 * @param sqlString: The SQL update query 
	 * @throws SQLException
	 */
	public void updateDatabase(String sqlString) throws SQLException {
		Statement statement = connection.createStatement();
		statement.executeUpdate(sqlString);
	}
	
	
	/**
	 * 
	 * Closes the connection to the database
	 * 
	 * @throws SQLException
	 */
	public void closeConnection() throws SQLException {
		connection.close();
	}
}
