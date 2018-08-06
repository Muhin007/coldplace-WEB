package com.github.muhin007.coldplaceweb.Servlets;

import com.github.muhin007.coldplaceweb.Data.City;
import com.github.muhin007.coldplaceweb.Data.ReadDB;
import com.github.muhin007.coldplaceweb.Data.Temp;
import com.github.muhin007.coldplaceweb.Data.WriteToDB;
import com.github.muhin007.coldplaceweb.PageGenerator;
import com.github.muhin007.coldplaceweb.Process;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;



public class URLColdplaceServlet extends HttpServlet {

    public static String cityName;
    public static String title;
    public static int cityTemp;
    int minTempCity;
    int maxTempCity;

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

                    List<City> cities = ReadDB.readCityFromDB();
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
                            Map<String, Object> pageVariablesEx = new HashMap<>();
                            pageVariablesEx.put("exceptionText", "Не получилось распознать страницу." +
                                    " Введите другой URL");
                            response.getWriter().println(PageGenerator.instance().
                                    getPage("exceptions.html", pageVariablesEx));
                            return;
                        }

                        title = doc.select("div [class=ArchiveTemp]").select("div *").
                                select("span[class=t_0]").removeAttr("style").removeAttr("class").text();

                        try {
                            Scanner s = new Scanner(title);
                            cityTemp = s.nextInt();
                        } catch (NoSuchElementException e) {
                            Map<String, Object> pageVariablesEx = new HashMap<>();
                            pageVariablesEx.put("exceptionText", "Нет данных о температуре" +
                                    " Введите другой URL");
                            response.getWriter().println(PageGenerator.instance().
                                    getPage("exceptions.html", pageVariablesEx));
                            return;

                        }


                        WriteToDB.writeToDB();

                        List<Temp> temps = ReadDB.readTempFromDB();
                        List<Integer> tempsCity = new ArrayList<>();
                        temps.forEach(temp -> {
                            if (temp.getCity().equalsIgnoreCase(cityName)) {
                                tempsCity.add(temp.getTemp());
                                minTempCity = Collections.min(tempsCity);
                                maxTempCity = Collections.max(tempsCity);
                            }
                        });


                        pageVariables.put("cityName", cityName);
                        pageVariables.put("cityTemp", cityTemp);
                        pageVariables.put("minTempCity", minTempCity);
                        pageVariables.put("maxTempCity", maxTempCity);
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

