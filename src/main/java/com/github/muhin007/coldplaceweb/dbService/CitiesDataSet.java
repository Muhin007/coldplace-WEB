package com.github.muhin007.coldplaceweb.dbService;



public class CitiesDataSet {
    public long id;
    public String city;
    public int temp;


    public CitiesDataSet(long id, String city, int temp) {
        this.id = id;
        this.city = city;
        this.temp = temp;
    }

    public String getCity() {
        return city;
    }

    public long getId() {
        return id;
    }
    public int getTemp() {return temp; }

    @Override
    public String toString() {
        return "CitiesDataSet{" + "id=" + id + ", city= " + city + ", temp= " + temp + '}';
    }
}
