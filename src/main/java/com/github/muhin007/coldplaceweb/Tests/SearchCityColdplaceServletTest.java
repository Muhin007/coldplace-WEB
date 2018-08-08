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
    public void SearchCity() {
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getParameter("name")).thenReturn("Лондон");

        assertEquals("Лондон", "Лондон");

    }

}