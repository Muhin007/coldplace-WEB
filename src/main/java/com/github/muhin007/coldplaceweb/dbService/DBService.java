package com.github.muhin007.coldplaceweb.dbService;


import org.h2.jdbcx.JdbcDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBService {

    private static Logger log = Logger.getLogger(DBService.class.getName());

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

            JdbcDataSource ds = new JdbcDataSource();
            ds.setURL(url);
            ds.setUser(name);
            ds.setPassword(pass);

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;
        } catch (SQLException e) {
            log.log(Level.SEVERE, "Exception: ", e);
            e.printStackTrace();
        }
        return null;
    }
}

