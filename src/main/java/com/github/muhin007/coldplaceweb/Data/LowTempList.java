package com.github.muhin007.coldplaceweb.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LowTempList {

    private List<Cities> lowTempList = DBConnection.ReadDB();

    private Cities city0 = lowTempList.get(0);
    private String cityName0 = city0.getName();
    private int lT0 = city0.calculateRandomTemperature();

    private Cities city1 = lowTempList.get(1);
    private String cityName1 = city1.getName();
    private int lT1 = city1.calculateRandomTemperature();

    private Cities city2 = lowTempList.get(2);
    private String cityName2 = city2.getName();
    private int lT2 = city2.calculateRandomTemperature();

    private Cities city3 = lowTempList.get(3);
    private String cityName3 = city1.getName();
    private int lT3 = city3.calculateRandomTemperature();

    private Cities city4 = lowTempList.get(4);
    private String cityName4 = city4.getName();
    private int lT4 = city4.calculateRandomTemperature();

    private Cities city5 = lowTempList.get(5);
    private String cityName5 = city5.getName();
    int lT5 = city5.calculateRandomTemperature();

    private Cities city6 = lowTempList.get(6);
    private String cityName6 = city6.getName();
    private int lT6 = city6.calculateRandomTemperature();

    private Cities city7 = lowTempList.get(7);
    private String cityName7 = city7.getName();
    private int lT7 = city7.calculateRandomTemperature();

    private Cities city8 = lowTempList.get(8);
    private String cityName8 = city8.getName();
    int lT8 = city8.calculateRandomTemperature();

    private Cities city9 = lowTempList.get(9);
    private String cityName9 = city9.getName();
    private int lT9 = city9.calculateRandomTemperature();

    private Cities city10 = lowTempList.get(10);
    private String cityName10 = city10.getName();
    private int lT10 = city10.calculateRandomTemperature();

    private Cities city11 = lowTempList.get(11);
    private String cityName11 = city11.getName();
    private int lT11 = city11.calculateRandomTemperature();

    private Cities city12 = lowTempList.get(12);
    private String cityName12 = city12.getName();
    private int lT12 = city12.calculateRandomTemperature();

    private Cities city13 = lowTempList.get(13);
    private String cityName13 = city13.getName();
    private int lT13 = city13.calculateRandomTemperature();


    public LowTempList(String name, int temperature) {

        List<LowTempList> lowTemperatureList = new ArrayList<>();
        lowTemperatureList.add(new LowTempList(cityName0, lT0));
        lowTemperatureList.add(new LowTempList(cityName1, lT1));
        lowTemperatureList.add(new LowTempList(cityName2, lT2));
        lowTemperatureList.add(new LowTempList(cityName3, lT3));
        lowTemperatureList.add(new LowTempList(cityName4, lT4));
        lowTemperatureList.add(new LowTempList(cityName5, lT5));
        lowTemperatureList.add(new LowTempList(cityName6, lT6));
        lowTemperatureList.add(new LowTempList(cityName7, lT7));
        lowTemperatureList.add(new LowTempList(cityName8, lT8));
        lowTemperatureList.add(new LowTempList(cityName9, lT9));
        lowTemperatureList.add(new LowTempList(cityName10, lT10));
        lowTemperatureList.add(new LowTempList(cityName11, lT11));
        lowTemperatureList.add(new LowTempList(cityName12, lT12));
        lowTemperatureList.add(new LowTempList(cityName13, lT13));

    }

}
