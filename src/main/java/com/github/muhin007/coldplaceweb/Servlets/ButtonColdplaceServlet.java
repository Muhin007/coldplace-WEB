package com.github.muhin007.coldplaceweb.Servlets;

import com.github.muhin007.coldplaceweb.Data.City;
import com.github.muhin007.coldplaceweb.Data.CityWithTemp;
import com.github.muhin007.coldplaceweb.Data.DBConnection;
import com.github.muhin007.coldplaceweb.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class ButtonColdplaceServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.getWriter().println(PageGenerator.instance().getPage("ButtonPage.html", new HashMap<>()));
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<CityWithTemp> citiesWithTemps = new ArrayList<>();
        List<City> cities = DBConnection.readDB();
        for (City currentCity : cities) {
            int t = currentCity.calculateRandomTemperature();
            citiesWithTemps.add(new CityWithTemp(currentCity, t));
        }
        int min = citiesWithTemps.get(0).temp;
        City coldestCity = citiesWithTemps.get(0).city;
        for (int i = 1; i < citiesWithTemps.size(); i++) {
            int t = citiesWithTemps.get(i).temp;
            if (t < min) {
                min = t;
                coldestCity = citiesWithTemps.get(i).city;
            }

        }
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("В " + coldestCity.getName() + " " + min);
        response.setContentType("text/html;charset=utf-8");
    }

}



