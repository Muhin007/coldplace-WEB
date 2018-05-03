package com.github.muhin007.coldplaceweb;

import java.sql.*;
import java.util.Vector;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/coldplace?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "superuser";
    private static final String password = "coldplaceweb";
    private Connection con = null;

    public Vector<Vector<Object>> getNomen(String query) {
        Vector<Vector<Object>> retVector = new Vector<>();
        {

            try (Connection con = DriverManager.getConnection(url, user, password);
                 Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int cols = rsmd.getColumnCount();
                while (rs.next()) {
                    Vector<Object> newRow = new Vector<>();
                    for (int i = 1; i <= cols; i++) {
                        newRow.add(rs.getObject(i));
                    }
                    retVector.add(newRow);
                }
            } catch (SQLException sqlEx)

            {
                sqlEx.printStackTrace();
            }
            return retVector;
        }

    }
}




