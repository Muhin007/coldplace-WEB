package com.github.muhin007.coldplaceweb.DataBase;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

    private static final String url = "jdbc:mysql://localhost:3306/coldplace?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "root";

    public static List<Cities> ReadDB() {

        String query = "select * from city";
        List<Cities> cities = null;
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            cities = new ArrayList<>();
            while (rs.next()) {
                Cities city = new Cities();
                city.setID(rs.getInt("id"));
                city.setName(rs.getString("name"));
                city.setMinTemperature(rs.getInt("min"));
                city.setMaxTemperature(rs.getInt("max"));
                cities.add(city);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
}





