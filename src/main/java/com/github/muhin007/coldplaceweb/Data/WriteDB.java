package com.github.muhin007.coldplaceweb.Data;

import com.github.muhin007.coldplaceweb.dbService.DBService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WriteDB {
    public static String city;
    public static String temp;

    public WriteDB() {
        String query = "insert into coldplace.citytemp (id, city, temp) values (id, city, temp) ";
        try (Connection con = DBService.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                stmt.executeUpdate(query);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
