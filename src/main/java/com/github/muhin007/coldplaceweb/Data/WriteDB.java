package com.github.muhin007.coldplaceweb.Data;

import com.github.muhin007.coldplaceweb.dbService.DBService;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class WriteDB {

    public static void WriteDB(String message, String title) {
        String query = "INSERT INTO coldplace.city (city, temp) \n" +
                " VALUES (city, temp);";
        try (Connection con = DBService.getConnection();
             Statement stmt = con.createStatement()) {
            stmt.executeUpdate(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
