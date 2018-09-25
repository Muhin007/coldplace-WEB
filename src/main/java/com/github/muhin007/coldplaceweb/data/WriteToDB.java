package com.github.muhin007.coldplaceweb.data;

import com.github.muhin007.coldplaceweb.dbservice.DBService;
import com.github.muhin007.coldplaceweb.servlets.SignInServlet;
import com.github.muhin007.coldplaceweb.servlets.URLColdplaceServlet;
import com.mysql.jdbc.PreparedStatement;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;


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

    public static void writeUserProfileToDB() throws SQLException {

        PreparedStatement preparedStmt = null;

        String query = "INSERT INTO coldplace.users (login, pass, email, role) \n" +
                "VALUES (?, ?, ?, ?);";
        Connection con = null;
        try {
            con = DBService.getConnection();
            con.setAutoCommit(false);
            preparedStmt = (PreparedStatement) con.prepareStatement(query);
            preparedStmt.setString(1, SignInServlet.login);
            preparedStmt.setString(2, SignInServlet.pass);
            preparedStmt.setString(3, SignInServlet.email);
            preparedStmt.setString(4, SignInServlet.role);
            preparedStmt.executeUpdate();
            con.commit();

        } catch (Exception e) {
            con.rollback();
//            if (con != null) {
//
//                try {
//                    con.rollback();
//                } catch (Exception ex) {
//                    log.error("Произошла ошибка. Подробности смотри в log-файле", ex);
//                }
//                println(PageGenerator.instance().getPage("noRegistrationData.html", new HashMap<>()));
//                log.error("Ошибка записи в БД. Не все данные введены в форму.", e);
//                return;
//            }
//
        } finally {
            if (preparedStmt != null) {
                preparedStmt.close();
            }


        }
    }
}