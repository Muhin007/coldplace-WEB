package com.github.muhin007.coldplaceweb.Data;

import com.github.muhin007.coldplaceweb.Servlets.URLColdplaceServlet;
import com.github.muhin007.coldplaceweb.dbService.DBService;
import com.mysql.jdbc.PreparedStatement;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;


public class WriteToDB {

    public static void writeToDB() {
        String query = "INSERT INTO coldplace.citytemp (city, temp, date) \n" +
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

           System.out.println("Запись в базу данных не сделана. Проверьте подключение или настройки БД.");
        }
    }
}
