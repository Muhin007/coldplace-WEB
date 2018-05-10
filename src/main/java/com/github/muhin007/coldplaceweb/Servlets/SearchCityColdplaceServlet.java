package com.github.muhin007.coldplaceweb.Servlets;

import com.github.muhin007.coldplaceweb.DataBase.Cities;
import com.github.muhin007.coldplaceweb.DataBase.DBConnection;
import com.github.muhin007.coldplaceweb.PageGenerator;

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

        List<Cities> cities = DBConnection.ReadDB();
        Cities foundedCity = null;
        for (Cities city : cities) {
            if (message.equalsIgnoreCase(city.getName())) {
                foundedCity = city;

                break;
            }
        }

        if (foundedCity != null) {
            response.getWriter().println("Сейчас в " + message + " " + foundedCity.calculateRandomTemperature());
            return;

        } else {
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("Города нет в списке");
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


