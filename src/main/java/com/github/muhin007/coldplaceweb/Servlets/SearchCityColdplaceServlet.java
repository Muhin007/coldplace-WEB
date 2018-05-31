package com.github.muhin007.coldplaceweb.Servlets;

import com.github.muhin007.coldplaceweb.Data.City;
import com.github.muhin007.coldplaceweb.Data.DBConnection;
import com.github.muhin007.coldplaceweb.PageGenerator;

import javax.servlet.ServletException;
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

        List<City> cities = DBConnection.readDB();
        City foundedCity = null;
        for (City city : cities) {
            if (message.equalsIgnoreCase(city.getName())) {
                foundedCity = city;

                break;
            }
        }

        if (foundedCity != null) {

            response.setContentType("text/html;charset=utf-8");
            pageVariables.put("cityName", message);
            pageVariables.put("minTemp", foundedCity.calculateRandomTemperature());
            response.getWriter().println(PageGenerator.instance().getPage("answerSearchCity.html", pageVariables));
            return;

        } else {
            response.setContentType("text/html;charset=utf-8");
            pageVariables.put("cityNotFound", "Города нет в списке");
            response.getWriter().println(PageGenerator.instance().getPage("index.html", pageVariables));

        }

    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }

}


