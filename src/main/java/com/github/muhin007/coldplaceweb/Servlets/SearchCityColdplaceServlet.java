package com.github.muhin007.coldplaceweb.Servlets;


import com.github.muhin007.coldplaceweb.Data.City;
import com.github.muhin007.coldplaceweb.Data.DBConnection;
import com.github.muhin007.coldplaceweb.PageGenerator;
import com.github.muhin007.coldplaceweb.Process;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCityColdplaceServlet extends HttpServlet {


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        Process.process(HttpServletRequest request, HttpServletRequest response, (HttpServletRequest req,
                                                                                  HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = createPageVariablesMap(req);
                    pageVariables.put("name", "");
                    resp.getWriter().println(PageGenerator.instance().getPage("index.html", pageVariables));
                }
        );

    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException{
        Process.process(HttpServletRequest request, HttpServletRequest response, (HttpServletRequest req,
                                                                                  HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = createPageVariablesMap(req);

                    String message = req.getParameter("name");

                    List<City> cities = DBConnection.readDB();
                    City foundedCity = null;
                    for (City city : cities) {
                        if (message.equalsIgnoreCase(city.getName())) {
                            foundedCity = city;

                            break;
                        }
                    }

                    if (foundedCity != null) {

                        pageVariables.put("cityName", message);
                        pageVariables.put("minTemp", foundedCity.calculateRandomTemperature());
                        resp.getWriter().println(PageGenerator.instance().getPage("answerSearchCity.html",
                                pageVariables));

                    } else {
                        pageVariables.put("cityNotFound", "Города нет в списке");
                        resp.getWriter().println(PageGenerator.instance().getPage("index.html", pageVariables));
                    }
                }
        );

    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }

}


