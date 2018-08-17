package com.github.muhin007.coldplaceweb.servlets;

import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

public class SearchCityColdplaceServletTest extends Mockito {


    @Test
    public void SearchCity() {
        String name = "Лондон";
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter(name)).thenReturn(name);

        assertEquals("Лондон", name);

    }

}