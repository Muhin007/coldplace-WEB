package com.github.muhin007.coldplaceweb.dbservice;

import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;

public class DBServiceTest {
    DBService connection = mock(DBService.class);

    @Test
    public void testConnection() {
        connection.getConnection();
        Assert.assertNotNull(connection.getConnection());
    }
}