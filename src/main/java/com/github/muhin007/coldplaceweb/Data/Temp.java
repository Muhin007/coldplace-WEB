package com.github.muhin007.coldplaceweb.Data;

import java.util.List;

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

//    public int calculateAverageTemperature() {
//        List<Temp> temps = ReadDB.readTempFromDB();
//        int summTemp = 0;
//
//        for (int i = 0; i < temps.size(); i++) {
//            summTemp = temps.get(i);
//        }
//        int numberCities = temps.indexOf(getCity());
//        double averageTemp = summTemp / numberCities;
//        return (int) averageTemp;
//    }
//
//    public Temp calculateMinMaxTemperature() {
//        List<Temp> temps = ReadDB.readTempFromDB();
//        Temp min = temps.get(0);
//        Temp max = temps.get(0);
//
//        for (Temp i : temps) {
//            if (i < min) {
//                min = i;
//            }
//            if (i > max) {
//                max = i;
//            }
//        }
//        return min;
//        return max;
//    }

}
