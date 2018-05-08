package com.github.muhin007.coldplaceweb.DataBase;

import java.util.Random;

public class City {
    private int id;
    private String name;
    private int min;
    private int max;


    void setID(int id) { this.id = id; }

    void setName(String name) {
        this.name = name;
    }

    void setMin(int min) {
        this.min = min;
    }

    void setMax(int max) {
        this.max = max;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + min + " " + max;
    }

    public String getName() {
        return name;
    }

    private int getMin() {
        return min;
    }


    private int getMax() {
        return max;
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
        result = 31 * result + min;
        result = 31 * result + max;
        return result;
    }

    public int calculateRandomTemperature() {
        Random r = new Random();
        int min = getMin();
        int max = getMax();
        double d = (Math.abs((max - min) * r.nextDouble()) + min);

        return (int) d;
    }
}
