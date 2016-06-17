/**
 * Name: Ricardo Vasquez
 * Course: CNT 4714 Summer 2016
 * Assignment title: Project 2 – JDBC
 * Date: June 16, 2016
 * Class: DatabaseConnection
 * Description: Abstraction of a database connection when given valid credentials
 */
import java.sql.*;

class DatabaseConnection {

    private Connection connection;
    private Statement statement;

    DatabaseConnection(String driver, String hostname, String username, String password) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connection = DriverManager.getConnection(hostname, username, password);
        statement = connection.createStatement();
    }

    void closeConnection() throws SQLException {
        connection.close();
    }

    ResultSet executeQuery(String query) throws SQLException {
        return statement.executeQuery(query);
    }

    int executeUpdate(String query) throws SQLException {
        return statement.executeUpdate(query);
    }
}
