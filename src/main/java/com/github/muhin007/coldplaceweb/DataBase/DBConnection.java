package com.github.muhin007.coldplaceweb.DataBase;

import java.sql.*;

public class DBConnection {
    private static final String url = "jdbc:mysql://localhost:3306/coldplace?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "superuser";
    private static final String password = "coldplaceweb";
    DBtoList list = new DBtoList();

    String query = "select * from city";

    {

        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            int i = 0;
            while (rs.next()) {
                list.setID(rs.getInt("id"));
                list.setName(rs.getString("name"));
                list.setMinTemp(rs.getInt("mintemp"));
                list.setMaxTemp(rs.getInt("maxtemp"));
                i++;
            }
            System.out.println(list.iDL());
            System.out.println(i);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}


