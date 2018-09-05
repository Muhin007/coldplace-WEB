package com.github.muhin007.coldplaceweb.servlets;

import com.github.muhin007.coldplaceweb.data.City;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.Assert.*;


public class SearchCityColdplaceServletTest extends Mockito {
    private HttpServletResponse getMockedResponse(StringWriter stringWriter) throws IOException {
        HttpServletResponse response = mock(HttpServletResponse.class);

        final PrintWriter writer = new PrintWriter(stringWriter);

        when(response.getWriter()).thenReturn(writer);

        return response;
    }

    private HttpServletRequest getMockedRequest(String url) {
        HttpSession httpSession = mock(HttpSession.class);
        HttpServletRequest request = mock(HttpServletRequest.class);

        when(request.getSession()).thenReturn(httpSession);
        when(request.getPathInfo()).thenReturn(url);

        return request;
    }

    @Test
    public void testSearchCity() throws IOException {
        final StringWriter stringWriter = new StringWriter();
        String name = "Лондон";
        HttpServletResponse response = getMockedResponse(stringWriter);
        HttpServletRequest request = getMockedRequest("/*");

        when(request.getParameter("cityName")).thenReturn(name);

        SearchCityColdplaceServlet searchCityColdplaceServlet = new SearchCityColdplaceServlet();
        searchCityColdplaceServlet.doGet(request, response);
        assertEquals("Лондон", name);

    }
}