package com.github.muhin007.coldplaceweb.Tests;

import com.github.muhin007.coldplaceweb.Servlets.SearchCityColdplaceServlet;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static javax.security.auth.callback.ConfirmationCallback.OK;
import static javax.servlet.http.HttpServletResponse.*;
import static org.junit.Assert.*;

public class SearchCityColdplaceServletTest extends Mockito {


    @Test
    public void SearchCity() throws IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        when(request.getParameter("name")).thenReturn("Лондон");

        assertEquals("Лондон", "Лондон");

        new SearchCityColdplaceServlet().doPost(request, response);

        verify(request, atLeast(1)).getParameter("name");
        assertEquals("Лондон", "Лондон");
    }

}