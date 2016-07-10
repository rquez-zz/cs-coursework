import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StartServlet extends HttpServlet {

    private MySQLConnection connection;
    private static final String UPDATE_STATUS =
            "update suppliers set `status` = `status` + 5 where snum IN " +
                    "(select distinct snum from shipments where quantity >= 100)";

    public void init(ServletConfig config) {
        // Open connection to MySQL Database
        connection = new MySQLConnection(
                config.getInitParameter("driver"),
                config.getInitParameter("hostname"),
                config.getInitParameter("username"),
                config.getInitParameter("password")
        );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String command = request.getParameter("command").trim().toLowerCase();
        session.setAttribute("error", "");
        session.setAttribute("rowsUpdated", "");

        try {
            if (command.startsWith("select"))
                session.setAttribute("rows", ResultSetTransformer.transformToTable(connection.executeQuery(command)));
            else {
                session.setAttribute("rowsUpdated", connection.executeUpdate(command));

                if (command.contains("shipments"))
                    session.setAttribute("statusesUpdated", connection.executeUpdate(UPDATE_STATUS));
            }
        } catch (SQLException e) {
            session.setAttribute("error", e.getMessage());
            e.printStackTrace();
        }

        session.setAttribute("command", command);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}
