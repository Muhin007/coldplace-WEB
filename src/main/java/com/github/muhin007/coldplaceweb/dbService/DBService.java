package com.github.muhin007.coldplaceweb.dbService;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBService {
    private static final Logger log = Logger.getLogger(DBService.class);

    public static Connection getConnection() {

        String url = "jdbc:mysql://localhost:3306/coldplace?useUnicode=true" +
                "&characterEncoding=utf8";
        String name = "root";
        String pass = "root";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, name, pass);
        } catch (SQLException e) {
            log.error("Произошла ошибка. Подробности смотри в log-файле", e);
        }
        return connection;

    }

}

