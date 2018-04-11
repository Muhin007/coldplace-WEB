package com.github.muhin007.coldplaceweb;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ColdplaceServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {


        String value = request.getParameter("key");

        if (value != null) {
            Map<String, Object> pageVariables = new HashMap<>();
            pageVariables.put("name", value);
            response.getWriter().println(PageGenerator.instance().getPage("index.ftl", pageVariables));

        }
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }
}
