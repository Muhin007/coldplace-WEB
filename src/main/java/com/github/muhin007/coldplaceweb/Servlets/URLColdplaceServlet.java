package com.github.muhin007.coldplaceweb.Servlets;

import com.github.muhin007.coldplaceweb.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class URLColdplaceServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        Map<String, Object> pageVariables = createPageVariablesMap(request);
        pageVariables.put("URL", "");

        response.getWriter().println(PageGenerator.instance().getPage("URLReadPage.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);

        String message = request.getParameter("URL");

        response.setContentType("text/html;charset=utf-8");

        URL coldplace = new URL(message);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(coldplace.openStream()))) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine.replaceAll(" ", " "));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=utf-8");
        pageVariables.put("lengthOfString", sb.toString().length());
        response.getWriter().println(PageGenerator.instance().getPage("summString.html", pageVariables));
    }


    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }
}
