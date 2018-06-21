package com.github.muhin007.coldplaceweb.Servlets;


import com.github.muhin007.coldplaceweb.Data.City;
import com.github.muhin007.coldplaceweb.Data.DBConnection;
import com.github.muhin007.coldplaceweb.PageGenerator;
import com.github.muhin007.coldplaceweb.Process;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class URLColdplaceServlet extends HttpServlet {


    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)throws IOException {

        Process.process(HttpServletRequest request, HttpServletRequest response, (HttpServletRequest req,
                                                                                  HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = createPageVariablesMap(req);
                    pageVariables.put("cityName", "");
                    resp.getWriter().println(PageGenerator.instance().getPage("URLReadPage.html", pageVariables));
                }
        );

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)throws IOException {
        Process.process(HttpServletRequest request, HttpServletRequest response, (HttpServletRequest req,
                                                                                  HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = createPageVariablesMap(req);

                    String message = req.getParameter("cityName");

                    List<City> cities = DBConnection.readDB();
                    City foundedCity = null;
                    for (City city : cities) {
                        if (message.equalsIgnoreCase(city.getName())) {
                            foundedCity = city;

                            break;
                        }
                    }

                    if (foundedCity != null) {

                        String url = foundedCity.getUrl();
                        URL coldplace = new URL(url);
                        StringBuilder sb = new StringBuilder();
                        try (BufferedReader in = new BufferedReader(
                                new InputStreamReader(coldplace.openStream()))) {

                            String inputLine;
                            while ((inputLine = in.readLine()) != null) {
                                sb.append(inputLine.replaceAll(" ", ""));
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        pageVariables.put("lengthOfString", sb.toString().length());
                        resp.getWriter().println(PageGenerator.instance().getPage("summString.html", pageVariables));
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

