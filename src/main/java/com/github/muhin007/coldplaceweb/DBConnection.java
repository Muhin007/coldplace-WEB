package com.github.muhin007.coldplaceweb;

import java.sql.*;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/coldplace?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "superuser";
    private static final String password = "coldplaceweb";

    String query = "select name from city";
    {

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String name = rs.getString("name");
                System.out.println(name);
            }
        } catch (SQLException sqlEx)

        {
            sqlEx.printStackTrace();
        }

    }
}

