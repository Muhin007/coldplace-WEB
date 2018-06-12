package com.github.muhin007.coldplaceweb.Data;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {

    private static final String url = "jdbc:mysql://localhost:3306/coldplace?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "root";

    public static List<City> readDB() {

        String query = "select * from city";
        List<City> cities = null;
        try (Connection con = DriverManager.getConnection(url, user, password);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            cities = new ArrayList<>();
            while (rs.next()) {
                City city = new City();
                city.setID(rs.getInt("id"));
                city.setName(rs.getString("name"));
                city.setMinTemperature(rs.getInt("min"));
                city.setMaxTemperature(rs.getInt("max"));
                city.setURL(rs.getString("url"));
                cities.add(city);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }
}





