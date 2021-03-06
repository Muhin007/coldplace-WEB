package com.github.muhin007.coldplacespring.data.beans;

import java.util.Random;

public class City {
    private int id;
    private String name;
    private int min;
    private int max;
    private  String url;

    public City() {
    }


    public void setID(int id) { this.id = id; }

    public void setName(String name) {
        this.name = name;
    }

    public void setMinTemperature(int minTemperature) {
        this.min = minTemperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        this.max = maxTemperature;
    }

    public void setURL(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return id + " " + name + " " + min + " " + max + " " + url;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    private int getMinTemperature() {
        return min;
    }

    private int getMaxTemperature() {
        return max;
    }

    public String getUrl() {
        return url;
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
        int minTemperature = getMinTemperature();
        int maxTemperature = getMaxTemperature();
        double d = (Math.abs((maxTemperature - minTemperature) * r.nextDouble()) + minTemperature);

        return (int) d;
    }
}