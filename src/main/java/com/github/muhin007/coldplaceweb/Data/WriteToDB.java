package com.github.muhin007.coldplaceweb.Data;

import com.github.muhin007.coldplaceweb.Servlets.URLColdplaceServlet;
import com.github.muhin007.coldplaceweb.dbService.DBService;
import com.github.muhin007.coldplaceweb.dbService.executor.Executor;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;

public class WriteToDB {

    public static void writeToDB() {
        String query = "INSERT INTO coldplace.citytemp (city, temp, date) \n" +
                " VALUES (?, ?, ?);";
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        try (Connection con = DBService.getConnection();
             PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query)) {

            preparedStmt.setString(1, URLColdplaceServlet.message);
            preparedStmt.setString(2, URLColdplaceServlet.title);
            preparedStmt.setObject(3, date);


            preparedStmt.executeUpdate();
            System.out.println("В БД добавлена запись: " + URLColdplaceServlet.message + " "
                    + URLColdplaceServlet.title + " " + date);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
