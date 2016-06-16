/**
 * Name: Ricardo Vasquez
 * Course: CNT 4714 Summer 2016
 * Assignment title: Project 2 – JDBC
 * Date: June 16, 2016
 * Class: ClientView
 * Description: GUI interface with action listener logic
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class ClientView extends JFrame {

    private static final String[] DRIVERS_LIST = new String[] {"com.mysql.jdbc.Driver", "oracle.jdbc.driver.OracleDriver"};
    private static final String[] HOSTNAME_LIST = new String[] {"jdbc:mysql://localhost:3306/project2", "jdbc:mysql://localhost:3306/test"};

    private DatabaseConnection db;

    // Start the GUI
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ClientView frame = new ClientView();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    // GUI Constructor
    private ClientView() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 500);

        JPanel panel = new JPanel();
        setContentPane(panel);
        panel.setLayout(null);

        // Close database connection when exiting
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (db != null) {
                    try {
                        db.closeConnection();
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(panel, e1.getMessage());
                    }
                }
                System.exit(0);
            }
        });

        JLabel driverLabel = new JLabel("JDBC Driver");
        driverLabel.setBounds(10, 35, 75, 20);
        panel.add(driverLabel);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(10, 95, 75, 20);
        panel.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 125, 75, 20);
        panel.add(passwordLabel);

        JLabel databaseUrlLabel = new JLabel("Hostname");
        databaseUrlLabel.setBounds(10, 65, 75, 20);
        panel.add(databaseUrlLabel);

        JComboBox<String> driverComboBox = new JComboBox<>(DRIVERS_LIST);
        driverComboBox.setSelectedIndex(0);
        driverComboBox.setBounds(93, 65, 250, 20);
        panel.add(driverComboBox);

        JComboBox<String> hostnameComboBox = new JComboBox<>(HOSTNAME_LIST);
        hostnameComboBox.setBounds(95, 35, 250, 20);
        panel.add(hostnameComboBox);

        JTextField usernameTextfield = new JTextField();
        usernameTextfield.setBounds(95, 95, 250, 20);
        panel.add(usernameTextfield);
        usernameTextfield.setColumns(10);

        JTextField passwordTextfield = new JTextField();
        passwordTextfield.setBounds(95, 125, 250, 20);
        panel.add(passwordTextfield);
        passwordTextfield.setColumns(10);

        JLabel databaseStatusLabel = new JLabel("No Connection");
        databaseStatusLabel.setForeground(Color.RED);
        databaseStatusLabel.setBounds(10, 155, 337, 14);
        panel.add(databaseStatusLabel);

        JButton connectButton = new JButton("Connect to Database");
        connectButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    // Check for an open database connection
                    if (db != null) {
                        db.closeConnection();
                    }

                    String driver = (String)driverComboBox.getSelectedItem();
                    String hostname = (String)hostnameComboBox.getSelectedItem();

                    // Create a new connection
                    db = new DatabaseConnection(
                            driver,
                            hostname,
                            usernameTextfield.getText(),
                            passwordTextfield.getText());

                    // Update the status label
                    databaseStatusLabel.setText("Connected to " + hostname);
                } catch (ClassNotFoundException | SQLException e1) {
                    JOptionPane.showMessageDialog(panel, e1.getMessage());
                }
            }
        });
        connectButton.setBounds(10, 180, 337, 25);
        panel.add(connectButton);

        JTextArea queryTextArea = new JTextArea();
        queryTextArea.setBounds(370, 35, 390, 136);
        panel.add(queryTextArea);

        JTable table = new JTable();
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVisible(true);
        scrollPane.setBounds(10, 235, 750, 171);
        panel.add(scrollPane);

        JButton executeQueryButton = new JButton("Execute SQL Command");
        executeQueryButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    String query = queryTextArea.getText().trim();

                    // Determine if query is select or update
                    if (query.startsWith("select")) {
                        ResultSet resultSet = db.executeQuery(query);
                        table.setModel(buildTableModel(resultSet));
                    } else {
                        int rows = db.executeUpdate(query);
                        JOptionPane.showMessageDialog(panel, rows + " rows affected.");
                        table.setModel(new DefaultTableModel());
                    }

                } catch (SQLException e1) {
                    JOptionPane.showMessageDialog(panel, e1.getMessage());
                }
            }
        });
        executeQueryButton.setBounds(585, 180, 175, 25);
        panel.add(executeQueryButton);

        JButton clearCommandButton = new JButton("Clear SQL");
        clearCommandButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                queryTextArea.setText("");
            }
        });
        clearCommandButton.setBounds(370, 180, 200, 25);
        panel.add(clearCommandButton);

        JButton clearResultButton = new JButton("Clear Result Window");
        clearResultButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                table.setModel(new DefaultTableModel());
            }
        });
        clearResultButton.setBounds(10, 415, 175, 23);
        panel.add(clearResultButton);

        JLabel enterInfoLabel = new JLabel("Enter Database Information");
        enterInfoLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        enterInfoLabel.setBounds(10, 10, 168, 20);
        panel.add(enterInfoLabel);

        JLabel executionResultLabel = new JLabel("SQL Execution Result");
        executionResultLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        executionResultLabel.setBounds(10, 215, 131, 14);
        panel.add(executionResultLabel);

        JLabel enterSqlLabel = new JLabel("Enter a SQL Command");
        enterSqlLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        enterSqlLabel.setBounds(370, 10, 150, 20);
        panel.add(enterSqlLabel);
    }

    /**
     * Builds a table model to be used by a JTable
     * @param resultSet
     * @return
     * @throws SQLException
     */
    private DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {

        ResultSetMetaData metaData = resultSet.getMetaData();

        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        Vector<Vector<Object>> data = new Vector<>();
        while (resultSet.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(resultSet.getObject(columnIndex));
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }
}
