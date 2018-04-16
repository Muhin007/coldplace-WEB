package com.github.muhin007.coldplaceweb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ColdplaceServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String city = request.getParameter("city");
        city = city.toUpperCase();
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("<!DOCTYPE HTML>");
        response.getWriter().println("<html><body><p>" + city + "</p></body></html>");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}