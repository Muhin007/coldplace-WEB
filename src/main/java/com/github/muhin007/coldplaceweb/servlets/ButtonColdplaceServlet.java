package com.github.muhin007.coldplaceweb.servlets;

import com.github.muhin007.coldplaceweb.data.City;
import com.github.muhin007.coldplaceweb.data.ReadDB;
import com.github.muhin007.coldplaceweb.PageGenerator;
import com.github.muhin007.coldplaceweb.Process;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ButtonColdplaceServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Process.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = createPageVariablesMap();
                    resp.getWriter().println(PageGenerator.instance().getPage("buttonPage.html", pageVariables));
                }
        );

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        Process.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = createPageVariablesMap();

                    List<City> cities = ReadDB.readCityFromDB();
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

                    pageVariables.put("cityName", coldestCity.getName());
                    pageVariables.put("minTemp", min);
                    resp.getWriter().println(PageGenerator.instance().getPage("answerButtonPage.html",
                            pageVariables));
                }
        );

    }

    private static Map<String, Object> createPageVariablesMap() {
        return new HashMap<>();
    }
}



