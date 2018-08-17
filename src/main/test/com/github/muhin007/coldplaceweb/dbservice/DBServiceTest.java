package com.github.muhin007.coldplaceweb.dbservice;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;

public class DBServiceTest {
    Connection connection = mock(Connection.class);

    @Test
    public void testConnection() throws SQLException {
        connection.isClosed();
        Assert.assertNotNull(connection.isClosed());
    }
}