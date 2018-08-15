package com.github.muhin007.coldplaceweb.dbService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBService {

    public static Connection getConnection() throws SQLException {

            String url = "jdbc:mysql://localhost:3306/coldplace?useUnicode=true" +
                    "&characterEncoding=utf8";
            String name = "root";
            String pass = "root";

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;

    }
}

