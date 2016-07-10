import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ResultSetTransformer {

    public static List<Map<String, Object>> transformToTable(ResultSet resultSet) throws SQLException {
        List<Map<String, Object>> rows = new ArrayList<>();
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();

        while (resultSet.next()) {
            Map<String, Object> columns = new LinkedHashMap<>();

            for (int i = 1; i <= columnCount; i++) {
                columns.put(metaData.getColumnLabel(i), resultSet.getObject(i));
            }

            rows.add(columns);
        }
        return  rows;
    }

}
