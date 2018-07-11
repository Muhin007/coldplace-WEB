package com.github.muhin007.coldplaceweb.Data;


import java.util.ArrayList;
import java.util.List;

import static com.github.muhin007.coldplaceweb.Servlets.URLColdplaceServlet.cityName;

public class Temp {

    private int id;
    private String city;
    private int temp;
    private String date;


    void setID(int id) {
        this.id = id;
    }

    void setCity(String city) {
        this.city = city;
    }

    void setTemp(int temp) {
        this.temp = temp;
    }

    void setDate(String date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return id + " " + city + " " + temp + " " + date;
    }

    public int getID() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public int getTemp() {
        return temp;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Temp temp = (Temp) o;
        return city.equals(temp.city);
    }

    @Override
    public int hashCode() {
        int result = city.hashCode();
        result = 31 * result + temp;
        return result;
    }

    public static double calculateAverageTemperature() {
        List<Temp> temps = ReadDB.readTempFromDB();
        for (Temp element : temps)
            if (element.getCity().equals(cityName)) {
                List<Integer> temp = new ArrayList<>();
                temp.add(element.getTemp());
                int summTemp = 0;

                for (int i = 0; i < temp.size(); i++) {
                    summTemp = temp.get(i);
                }
                int numberCities = temp.size();
                double averageTemp = summTemp / numberCities;
                return averageTemp;
            }
        return 0;
    }

        public static int calculateMinTemperature() {
            List<Temp> temps = ReadDB.readTempFromDB();
            for (Temp element : temps)
                if (element.getCity().equals(cityName)) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(element.getTemp());
                    int min = temp.get(0);
                    for (int i : temp) {
                        min = min < i ? min : i;
                    }
                    return min;
                }

            return 0;
        }
    }