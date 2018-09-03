package com.github.muhin007.coldplaceweb.data;

import com.github.muhin007.coldplaceweb.dbservice.DBService;
import com.github.muhin007.coldplaceweb.servlets.AuthorizationServlet;
import com.github.muhin007.coldplaceweb.servlets.URLColdplaceServlet;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
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

    public static void writeUserToDB() {

        String query = "INSERT INTO coldplace.users (login, pass, email, role) \n" +
                "VALUES (?, ?, ?, ?);";
        Connection con = null;
        try {
            con = DBService.getConnection();
            con.setAutoCommit(false);
            PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query);
            preparedStmt.setString(1, AuthorizationServlet.login);
            preparedStmt.setString(2, AuthorizationServlet.pass);
            preparedStmt.setString(3, AuthorizationServlet.email);
            preparedStmt.setString(4, AuthorizationServlet.role);
            preparedStmt.executeUpdate();
            con.commit();

        } catch (Exception e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (Exception ex) {
                }
            }
            e.printStackTrace();
        } finally

        {
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
