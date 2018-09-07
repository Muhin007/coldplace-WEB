package com.github.muhin007.coldplaceweb.data;

import com.github.muhin007.coldplaceweb.PageGenerator;
import com.github.muhin007.coldplaceweb.dbservice.DBService;
import com.github.muhin007.coldplaceweb.servlets.RegistrationServlet;
import com.github.muhin007.coldplaceweb.servlets.URLColdplaceServlet;
import com.mysql.jdbc.PreparedStatement;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;

import static java.sql.DriverManager.println;


public class WriteToDB {
    private static final Logger log = Logger.getLogger(WriteToDB.class);

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
            preparedStmt.execute();
            preparedStmt.setString(2, RegistrationServlet.pass);
            preparedStmt.execute();
            preparedStmt.setString(3, RegistrationServlet.email);
            preparedStmt.execute();
            preparedStmt.setString(4, RegistrationServlet.role);
            preparedStmt.executeUpdate();
            con.commit();

        } catch (Exception e) {
            if (con != null) {

                try {
                    con.rollback();
                } catch (Exception ex) {
                    log.error("Произошла ошибка. Подробности смотри в log-файле", ex);
                }
                println(PageGenerator.instance().getPage("noRegistrationData.html", new HashMap<>()));
                log.error("Ошибка записи в БД. Не все данные введены в форму.", e);
                return;
            }

        } finally {
            if (preparedStmt != null) {
                preparedStmt.close();
            }
            con.setAutoCommit(true);

        }
    }
}