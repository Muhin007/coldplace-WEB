package com.github.muhin007.coldplacespring.data;

import com.github.muhin007.coldplacespring.dbService.DBService;
import com.github.muhin007.coldplacespring.servlets.URLColdplaceServlet;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;


public class WriteToDB {
    private static final Logger log = Logger.getLogger(WriteToDB.class);

    public static void addTemp() throws Exception {
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

    public static void addUserProfile(String login, String pass, String email, String role) {

        PreparedStatement preparedStmt = null;

        String query = "INSERT INTO coldplace.users (login, pass, email, role) \n" +
                "VALUES (?, ?, ?, ?);";
        Connection con = null;
        try {
            con = DBService.getConnection();
            con.setAutoCommit(false);
            preparedStmt = (PreparedStatement) con.prepareStatement(query);
            preparedStmt.setString(1, login);
            preparedStmt.setString(2, pass);
            preparedStmt.setString(3, email);
            preparedStmt.setString(4, role);
            preparedStmt.executeUpdate();
            con.commit();

        } catch (SQLException e) {
            log.error("Произошла ошибка. Подробности смотри в log-файле", e);

            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    log.error("Произошла ошибка. Подробности смотри в log-файле", ex);
                }
            }

        } finally {
            if (preparedStmt != null) {
                try {
                    preparedStmt.close();
                } catch (SQLException e) {
                    log.error("Произошла ошибка. Подробности смотри в log-файле", e);
                }
            }
        }
    }
}