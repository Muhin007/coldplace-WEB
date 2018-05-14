package com.github.muhin007.coldplaceweb.Servlets;

import com.github.muhin007.coldplaceweb.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ButtonColdplaceServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);
        pageVariables.put("PRESS", "PRESS");

        response.getWriter().println(PageGenerator.instance().getPage("ButtonPage.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, Object> pageVariables = createPageVariablesMap(request);
        response.setContentType("text/html;charset=utf-8");
        if (request.getParameter("PRESS")!= null) {
                response.getWriter().println("тут будет показана температура");
                response.setContentType("text/html;charset=utf-8");
            return;
            }
        else {
            response.getWriter().println("ошибочка вышла");
        }
    }
    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("PRESS", request.getParameterMap().toString());
        return pageVariables;
    }
}
