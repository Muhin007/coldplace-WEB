package com.github.muhin007.coldplaceweb.Servlets;

import com.github.muhin007.coldplaceweb.Data.City;
import com.github.muhin007.coldplaceweb.Data.ReadDB;
import com.github.muhin007.coldplaceweb.PageGenerator;
import com.github.muhin007.coldplaceweb.Process;
import com.github.muhin007.coldplaceweb.dbService.DBService;
import com.mysql.jdbc.PreparedStatement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class URLColdplaceServlet extends HttpServlet {

    private static String message;
    private static String title;

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

                    message = req.getParameter("cityName");

                    List<City> cities = ReadDB.readDB();
                    City foundedCity = null;
                    for (City city : cities) {
                        if (message.equalsIgnoreCase(city.getName())) {
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
                            e.printStackTrace();
                        }
                        title = doc.select("div [id=ArchTemp]").select("div *").
                                select("span[class=t_0]").removeAttr("style").removeAttr("class").text();

                        String query = "INSERT INTO coldplace.citytemp (city, temp, date) \n" +
                                " VALUES (?, ?, ?);";
                        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
                        try (Connection con = DBService.getConnection();
                             PreparedStatement preparedStmt = (PreparedStatement) con.prepareStatement(query)) {

                            preparedStmt.setString(1, message);
                            preparedStmt.setString(2, title);
                            preparedStmt.setObject(3, date);


                            preparedStmt.executeUpdate();
                            System.out.println("В БД добавлена запись: " + message + " " + title + " " + date);

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        pageVariables.put("cityName", message);
                        pageVariables.put("cityTemp", title);
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

