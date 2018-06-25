package com.github.muhin007.coldplaceweb.dbService;

public class UsersDataSet {
    public long id;
    public String cityName;
    public int temp;

    public UsersDataSet(long id, String name) {
        this.id = id;
        this.cityName = cityName;
        this.temp = temp;
    }

    public String getCityName() {
        return cityName;
    }

    public long getId() {
        return id;
    }
    public int getTemp() {return temp; }

    @Override
    public String toString() {
        return "UsersDataSet{" + "id=" + id + ", cityName= " + cityName + temp + '}';
    }
}
