package com.github.muhin007.coldplaceweb.DataBase;

import java.util.Random;

class City {
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

    public String getName() {
        return name;
    }

    private int getMinTemp() {
        return mintemp;
    }


    private int getMaxTemp() {
        return maxtemp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;
        return name.equals(city.name);
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
