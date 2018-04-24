package com.github.muhin007.coldplaceweb;

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

    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String title = "Список городов в БД";
        String docType = "<!DOCTYPE html>";

        String query = "select name from city";

        {

            try {

                con = DriverManager.getConnection(url, user, password);

                stmt = con.createStatement();

                rs = stmt.executeQuery(query);

                writer.println(docType + "<html><head><title>" + title + "</title></head><body>");
                writer.println("<h1>Список городов в БД</h1>");
                writer.println("<br/>");
                while (rs.next()) {
                    String name = rs.getString("name");
                    writer.println(name + "<br/>");

                }

            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            } finally {

                try {
                    con.close();
                } catch (SQLException se) {
                }
                try {
                    stmt.close();
                } catch (SQLException se) {
                }
                try {
                    rs.close();
                } catch (SQLException se) {
                }
                writer.println("</body></html>");
            }

        }
    }
}