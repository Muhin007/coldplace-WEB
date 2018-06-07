package com.github.muhin007.coldplaceweb.Servlets;

import com.github.muhin007.coldplaceweb.Action;
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
    class ActionImpl implements Action {

        @Override
        public void action(HttpServletRequest request, HttpServletResponse response) throws IOException {
            Map<String, Object> pageVariables = createPageVariablesMap(request);
            pageVariables.put("URL", "");
            response.getWriter().println(PageGenerator.instance().getPage("URLReadPage.html", pageVariables));
        }
    }

    static void process(HttpServletRequest request,
                        HttpServletResponse response, Action action) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        action.action(request, response);

        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        URLColdplaceServlet.process(request, response,  (HttpServletRequest req, HttpServletResponse resp) -> {
                Map<String, Object> pageVariables = createPageVariablesMap(req);
                pageVariables.put("URL", "");
                resp.getWriter().println(PageGenerator.instance().getPage("URLReadPage.html", pageVariables));
            }
        );


    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        URLColdplaceServlet.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {
            Map<String, Object> pageVariables = createPageVariablesMap(req);
            String message = req.getParameter("URL");
                        URL coldplace = new URL(message);
                    StringBuilder sb = new StringBuilder();
                    try (BufferedReader in = new BufferedReader(
                            new InputStreamReader(coldplace.openStream()))) {

                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                            sb.append(inputLine.replaceAll(" ", ""));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    pageVariables.put("lengthOfString", sb.toString().length());
                    resp.getWriter().println(PageGenerator.instance().getPage("summString.html", pageVariables));
        }
        );
}

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }
}
