package com.github.muhin007.coldplaceweb;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class ColdplaceServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String city = request.getParameter("city");

        if (city != null) {
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put("name", city);

            city = city.toUpperCase();
            response.getWriter().println(PageGenerator.instance().getPage("index.ftl", pageVariables));
            response.getWriter().println("<!DOCTYPE HTML>");
            response.getWriter().println("<html><body><p>" + city + "</p></body></html>");
        }
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }
}