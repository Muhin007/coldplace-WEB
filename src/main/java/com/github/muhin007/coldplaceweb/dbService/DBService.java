package com.github.muhin007.coldplaceweb.dbService;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBService {

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
            e.printStackTrace();
        }
        return null;
    }
}

