package com.github.muhin007.coldplaceweb.data;


import com.github.muhin007.coldplaceweb.dbservice.DBService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.github.muhin007.coldplaceweb.servlets.SignInServlet.*;

public class ReadDB {

    public static List<City> readCityFromDB() throws SQLException {
        String query = "select * from city";
        List<City> cities = new ArrayList<>();
        try (Connection con = DBService.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                City city = new City();
                city.setID(rs.getInt("id"));
                city.setName(rs.getString("name"));
                city.setMinTemperature(rs.getInt("min"));
                city.setMaxTemperature(rs.getInt("max"));
                city.setURL(rs.getString("url"));
                cities.add(city);
            }
        }
        return cities;
    }

    public static List<Temp> readTempFromDB() throws SQLException {
        String query = "select * from cityTemp";
        List<Temp> temps = null;
        try (Connection con = DBService.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            temps = new ArrayList<>();
            while (rs.next()) {
                Temp temp = new Temp();
                temp.setID(rs.getInt("id"));
                temp.setCity(rs.getString("city"));
                temp.setTemp(rs.getInt("temp"));
                temp.setDate(rs.getString("date"));
                temps.add(temp);
            }
        }
        return temps;
    }

    public  static List<UserProfile> readUserFromDB() throws  SQLException {
        String query = "select * from users";
        List<UserProfile> userProfiles = new ArrayList<>();
        try (Connection con = DBService.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                UserProfile userProfile = new UserProfile(login, pass);
                userProfile.setLogin(rs.getString("login"));
                userProfile.setPass(rs.getString("pass"));
                userProfile.setEmail(rs.getString("email"));
                userProfile.setRole(rs.getString("role"));
                userProfiles.add(userProfile);
            }
        }
        return userProfiles;
    }
}





