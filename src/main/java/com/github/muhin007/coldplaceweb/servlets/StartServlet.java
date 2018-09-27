package com.github.muhin007.coldplaceweb.servlets;

import com.github.muhin007.coldplaceweb.PageGenerator;
import com.github.muhin007.coldplaceweb.Process;
import com.github.muhin007.coldplaceweb.data.AccountService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class StartServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        Process.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = createPageVariablesMap();
                    resp.getWriter().println(PageGenerator.instance().getPage("start.html", pageVariables));
                }
        );
    }

    private static Map<String, Object> createPageVariablesMap() {
        return new HashMap<>();
    }
}
