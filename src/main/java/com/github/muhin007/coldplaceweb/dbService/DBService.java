package com.github.muhin007.coldplaceweb.dbService;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBService {

    private static final Logger log = Logger.getLogger(DBService.class);

    private final Connection connection;
    public DBService() {
        this.connection = getConnection();
    }


    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/coldplace?useUnicode=true" +
                    "&characterEncoding=utf8";
            String name = "root";
            String pass = "root";

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }
}

