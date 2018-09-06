package com.github.muhin007.coldplaceweb.data;

import com.github.muhin007.coldplaceweb.dbservice.DBService;
import com.github.muhin007.coldplaceweb.servlets.RegistrationServlet;
import com.github.muhin007.coldplaceweb.servlets.URLColdplaceServlet;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;


public class WriteToDB {
    public static void writeToDB() throws Exception {
        String query = "INSERT INTO coldplace.cityTemp (city, temp, date) \n" +
                " VALUES (?, ?, ?);";

        String date = new Timestamp(new java.util.Date().getTime()).toString();

        try (Connection con = DBService.getConnection();
             PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query)) {

            preparedStmt.setString(1, URLColdplaceServlet.cityName);
            preparedStmt.setInt(2, URLColdplaceServlet.cityTemp);
            preparedStmt.setObject(3, date);
            preparedStmt.executeUpdate();
        }
    }

    public static void writeUserToDB() throws SQLException {

        PreparedStatement preparedStmt = null;

        String query = "INSERT INTO coldplace.users (login, pass, email, role) \n" +
                "VALUES (?, ?, ?, ?);";
        Connection con = null;
        try {
            con = DBService.getConnection();
            con.setAutoCommit(false);
            preparedStmt = (PreparedStatement) con.prepareStatement(query);
            preparedStmt.setString(1, RegistrationServlet.login);
            preparedStmt.executeUpdate();
            preparedStmt.setString(2, RegistrationServlet.pass);
            preparedStmt.executeUpdate();
            preparedStmt.setString(3, RegistrationServlet.email);
            preparedStmt.executeUpdate();
            preparedStmt.setString(4, RegistrationServlet.role);
            preparedStmt.executeUpdate();
            con.commit();

        } catch (Exception e) {
            if (con != null) {
                try {
                    System.err.print("Transaction is being rolled back ");
                    con.rollback();
                } catch (Exception ex) {
                }
            }
            e.printStackTrace();
        } finally {
            if (preparedStmt != null) {
                preparedStmt.close();
            }
            con.setAutoCommit(true);

        }
    }
}
