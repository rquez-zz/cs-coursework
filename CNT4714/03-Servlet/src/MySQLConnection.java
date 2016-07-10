/**
 * Name: Ricardo Vasquez
 * Course: CNT 4714 Summer 2016
 * Assignment title: Project 2 – JDBC
 * Date: June 16, 2016
 * Class: MySQLConnection
 * Description: Abstraction of a database connection when given valid credentials
 */
import java.sql.*;

class MySQLConnection {

    private Connection connection;
    private Statement statement;

    MySQLConnection(
            String driver,
            String hostname,
            String username,
            String password) {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(hostname, username, password);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
