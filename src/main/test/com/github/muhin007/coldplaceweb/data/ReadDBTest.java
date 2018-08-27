package com.github.muhin007.coldplaceweb.data;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;

public class ReadDBTest {

    @Test
    public void readCityFromDB() {
        ReadDB readDB = mock(ReadDB.class);
        List<City> cities = readDB.readCityFromDB();
        Assert.assertEquals(13, cities.size());
    }
}