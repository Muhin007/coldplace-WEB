package com.github.muhin007.coldplaceweb.Data;


import com.github.muhin007.coldplaceweb.dbService.DBService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReadDB {

    public static List<City> readCityFromDB() {

        String query = "select * from city";
        List<City> cities = null;
        try (Connection con = DBService.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            cities = new ArrayList<>();
            while (rs.next()) {
                City city = new City();
                city.setID(rs.getInt("id"));
                city.setName(rs.getString("name"));
                city.setMinTemperature(rs.getInt("min"));
                city.setMaxTemperature(rs.getInt("max"));
                city.setURL(rs.getString("url"));
                cities.add(city);

            }

        } catch (SQLException e) {
            System.out.println("нет подключения к БД");
        }
        return cities;
    }

    public static List<Temp> readTempFromDB() {
        String query = "select * from cityTemp";
        List<Temp> temps = new ArrayList<>();
        try (Connection con = DBService.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Temp temp = new Temp();
                temp.setID(rs.getInt("id"));
                temp.setCity(rs.getString("city"));
                temp.setTemp(rs.getInt("temp"));
                temp.setDate(rs.getString("date"));
                temps.add(temp);

            }

        } catch (SQLException e) {
            System.out.println("нет подключения к БД");
        }
        return temps;
    }

}





