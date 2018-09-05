package com.github.muhin007.coldplaceweb;

import com.github.muhin007.coldplaceweb.data.City;
import org.junit.Assert;
import org.junit.Test;

public class CityTest {
    public CityTest() {
    }

    @Test
    public void calculateRandomTemperature() {
        City city = new City();

        for(int i = 0; i < 10; ++i) {
            int t = city.calculateRandomTemperature();
            Assert.assertTrue(t >= -30 && t < 10);
        }

    }
}
