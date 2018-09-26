package com.github.muhin007.coldplaceweb.dbservice;

import com.github.muhin007.coldplaceweb.data.UsersDataSet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.github.muhin007.coldplaceweb.servlets.SignUpServlet.role;


public class DBService {

    public static Connection getConnection() throws SQLException {

            String url = "jdbc:mysql://localhost:3306/coldplace?useUnicode=true" +
                    "&characterEncoding=utf8";
            String name = "root";
            String pass = "root";

            Connection connection = DriverManager.getConnection(url, name, pass);
            return connection;

    }

    public void addUser(String login, String pass, String email) {
    }

    public UsersDataSet getUserByLogin(String login) {
        return null;
    }

}

