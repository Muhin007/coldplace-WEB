package com.github.muhin007.coldplaceweb.Servlets;

import com.github.muhin007.coldplaceweb.Action;
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

    static void process(HttpServletRequest request,
                        HttpServletResponse response, Action action) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        action.action(request, response);

        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ButtonColdplaceServlet.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = createPageVariablesMap(req);
                    resp.getWriter().println(PageGenerator.instance().getPage("buttonPage.html", pageVariables));
                }
        );

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ButtonColdplaceServlet.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = createPageVariablesMap(req);

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

                    pageVariables.put("cityName", coldestCity.getName());
                    pageVariables.put("minTemp", min);
                    resp.getWriter().println(PageGenerator.instance().getPage("answerButtonPage.html",
                            pageVariables));
                }
        );

    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        return pageVariables;
    }
}



