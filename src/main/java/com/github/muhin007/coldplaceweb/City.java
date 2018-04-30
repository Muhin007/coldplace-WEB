package com.github.muhin007.coldplaceweb;

import java.util.Random;

class City {


    private String name;
    private int minTemperature;
    private int maxTemperature;

    City(String name, int minTemperature, int maxTemperature) {
        this.name = name;
        this.minTemperature = minTemperature;
        this.maxTemperature = maxTemperature;
    }

    public String getName() {
        return name;
    }

    public int getMinTemperature() {
        return minTemperature;
    }


    public int getMaxTemperature() {
        return maxTemperature;
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
        result = 31 * result + minTemperature;
        result = 31 * result + maxTemperature;
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

