package com.github.muhin007.coldplaceweb.Servlets;

import com.github.muhin007.coldplaceweb.Data.City;
import com.github.muhin007.coldplaceweb.Data.ReadDB;
import com.github.muhin007.coldplaceweb.Data.WriteToDB;
import com.github.muhin007.coldplaceweb.PageGenerator;
import com.github.muhin007.coldplaceweb.Process;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class URLColdplaceServlet extends HttpServlet {

    public static String cityName;
    public static String title;
    public static int cityTemp;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        Process.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = createPageVariablesMap(req);
                    pageVariables.put("cityName", "");
                    resp.getWriter().println(PageGenerator.instance().getPage("URLReadPage.html", pageVariables));
                }
        );

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        Process.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = createPageVariablesMap(req);

                    cityName = req.getParameter("cityName");

                    List<City> cities = ReadDB.readDB();
                    City foundedCity = null;
                    for (City city : cities) {
                        if (cityName.equalsIgnoreCase(city.getName())) {
                            foundedCity = city;

                            break;
                        }
                    }

                    if (foundedCity != null) {

                        String url = foundedCity.getUrl();
                        Document doc = null;
                        try {
                            doc = Jsoup.connect(url).get();
                        } catch (IOException e) {
                            Map<String, Object> pageVariablesEx = createPageVariablesMap(request);
                            pageVariablesEx.put("exceptionText", "Не получилось распознать страницу." +
                                    " Введите другой URL");
                            response.getWriter().println(PageGenerator.instance().
                                    getPage("exceptions.html", pageVariables));
                        }
                        title = doc.select("div [id=ArchTemp]").select("div *").
                                select("span[class=t_0]").removeAttr("style").removeAttr("class").text();

                       try {
                           Scanner s = new Scanner(title);
                           cityTemp = s.nextInt();
                       } catch(java.util.NoSuchElementException e){
                           System.out.println("нет данных о температуре");
                       }


                        WriteToDB.writeToDB();

                        pageVariables.put("cityName", cityName);
                        pageVariables.put("cityTemp", cityTemp);
                        resp.getWriter().println(PageGenerator.instance().
                                getPage("URLReadPageAnswer.html", pageVariables));


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

