package com.github.muhin007.coldplaceweb.Servlets;

import com.github.muhin007.coldplaceweb.DataBase.City;
import com.github.muhin007.coldplaceweb.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ColdplaceServletSearchCity extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        Map<String, Object> pageVariables = createPageVariablesMap(request);
        pageVariables.put("name", "");

        response.getWriter().println(PageGenerator.instance().getPage("index.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);

        String message = request.getParameter("name");

        response.setContentType("text/html;charset=utf-8");

        Class<City> cities = City.class;
        City foundedCity = null;
        for (City city : cities){
            if (message.equalsIgnoreCase(city.getName())) {
                foundedCity = city;

                break;
            }

        if (foundedCity == null || message.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            System.out.println("Вы не указали название города или его нет в списке");
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            System.out.println("Сейчас в " + message + " " + foundedCity.calculateRandomTemperature());
        }
        pageVariables.put("name", message == null ? "" : message);

        response.getWriter().println(PageGenerator.instance().getPage("index.html", pageVariables));
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }

}

