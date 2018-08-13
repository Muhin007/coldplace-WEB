package com.github.muhin007.coldplaceweb.Data;


import com.github.muhin007.coldplaceweb.dbService.DBService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReadDB {

    private static final Logger log = Logger.getLogger(ReadDB.class);

    public static List<City> readCityFromDB() {

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

        } catch (SQLException e) {
            log.error("нет подключения к БД", e);
        }
        return cities;
    }
    public  static List<Temp> readTempFromDB(){
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

        } catch (SQLException e) {
            log.error("нет подключения к БД", e);
        }
        return temps;
    }

}





