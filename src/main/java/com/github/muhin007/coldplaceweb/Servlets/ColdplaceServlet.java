package com.github.muhin007.coldplaceweb.Servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


public class ColdplaceServlet extends HttpServlet {

    private static final String url = "jdbc:mysql://localhost:3306/coldplace?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String user = "superuser";
    private static final String password = "coldplaceweb";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String title = "Список городов в БД";
        String docType = "<!DOCTYPE html>";

        String query = "select name from city";

        {

            try (Connection con = DriverManager.getConnection(url, user, password);
                 Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {

                writer.println(docType + "<html><head><title>" + title + "</title></head><body>");
                writer.println("<h1>Список городов в БД</h1>");
                writer.println("<br/>");
                while (rs.next()) {
                    String name = rs.getString("name");
                    writer.println(name + "<br/>");

                }

            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
            writer.println("</body></html>");
        }

    }
}
