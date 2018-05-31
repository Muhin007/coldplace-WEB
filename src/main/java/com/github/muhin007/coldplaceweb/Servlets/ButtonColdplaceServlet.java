package com.github.muhin007.coldplaceweb.Servlets;

import com.github.muhin007.coldplaceweb.Data.City;
import com.github.muhin007.coldplaceweb.Data.DBConnection;
import com.github.muhin007.coldplaceweb.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ButtonColdplaceServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);
        response.getWriter().println(PageGenerator.instance().getPage("buttonPage.html", pageVariables));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);

        List<City> cities = DBConnection.readDB();
        City coldestCity = cities.get(0);
        int min = coldestCity.calculateRandomTemperature();

        for (int i = 1; i < cities.size(); i++) {
            City currentCity = cities.get(i);
            int t = currentCity.calculateRandomTemperature();
            if (t < min) {
                min = t;
                coldestCity = currentCity;
            }
        }

        response.setContentType("text/html;charset=utf-8");
        pageVariables.put("cityName", coldestCity.getName());
        pageVariables.put("minTemp", min);
        response.getWriter().println(PageGenerator.instance().getPage("answerButtonPage.html", pageVariables));
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        return pageVariables;
    }
}



