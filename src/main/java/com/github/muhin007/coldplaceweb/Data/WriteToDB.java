package com.github.muhin007.coldplaceweb.Data;

import com.github.muhin007.coldplaceweb.Servlets.URLColdplaceServlet;
import com.github.muhin007.coldplaceweb.dbService.DBService;
import com.mysql.jdbc.PreparedStatement;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;


public class WriteToDB {

    private static final Logger log = Logger.getLogger(WriteToDB.class);

    public static void writeToDB() {
        String query = "INSERT INTO coldplace.cityTemp (city, temp, date) \n" +
                " VALUES (?, ?, ?);";

       String date = new Timestamp(new java.util.Date().getTime()).toString();


        try (Connection con = DBService.getConnection();
             PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query)) {

            preparedStmt.setString(1, URLColdplaceServlet.cityName);
            preparedStmt.setInt(2, URLColdplaceServlet.cityTemp);
            preparedStmt.setObject(3, date);
            preparedStmt.executeUpdate();

            System.out.println("В БД добавлена запись: " + URLColdplaceServlet.cityName + " "
                    + URLColdplaceServlet.cityTemp + " " + date);

        } catch (SQLException e) {
            log.error("Запись в базу данных не сделана. Проверьте подключение или настройки БД.", e);
        }
    }
}
