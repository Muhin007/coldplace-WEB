package com.github.muhin007.coldplaceweb.DataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class DBtoList {
    private int id;
    private String name;
    private int mintemp;
    private int maxtemp;


    void setID(int id) {
        this.id = id;
    }

    void setName(String name) {
        this.name = name;
    }

    void setMinTemp(int mintemp) {
        this.mintemp = mintemp;
    }

    void setMaxTemp(int maxtemp) {
        this.maxtemp = maxtemp;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + mintemp + " " + maxtemp;
    }

    public List<DBtoList> iDL() {
        List<DBtoList> Drt = new ArrayList<>();
        Drt.add(new DBtoList());
        return Drt;
    }

    public String getName() {
        return name;
    }

    public int getMinTemp() {
        return mintemp;
    }


    public int getMaxTemp() {
        return maxtemp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DBtoList dBtoList = (DBtoList) o;
        return name.equals(dBtoList.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + mintemp;
        result = 31 * result + maxtemp;
        return result;
    }

    public int calculateRandomTemperature() {
        Random r = new Random();
        int mintemp = getMinTemp();
        int maxtemp = getMaxTemp();
        double d = (Math.abs((maxtemp - mintemp) * r.nextDouble()) + mintemp);

        return (int) d;
    }
}
