package com.github.muhin007.coldplacespring.servlets;

import com.github.muhin007.coldplaceweb.PageGenerator;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ApplicationPickerServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {

        Process.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put("message", "Выбери приложение.");
                    resp.getWriter().println(PageGenerator.instance().
                            getPage("appsPage.html", pageVariables));
                }
        );
    }
}
