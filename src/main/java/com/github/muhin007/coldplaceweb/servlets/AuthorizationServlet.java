package com.github.muhin007.coldplaceweb.servlets;

import com.github.muhin007.coldplaceweb.PageGenerator;
import com.github.muhin007.coldplaceweb.Process;
import com.github.muhin007.coldplaceweb.data.WriteToDB;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class AuthorizationServlet extends HttpServlet {

    public static String login;
    public static String pass;
    public static String email;

    private static final Logger log = org.apache.log4j.Logger.getLogger(URLColdplaceServlet.class);

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {

        Process.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = createPageVariablesMap(req);
                    pageVariables.put("login", "");
                    pageVariables.put("pass", "");
                    pageVariables.put("email", "");
                    resp.getWriter().println(PageGenerator.instance().getPage("authorization.html", pageVariables));
                }
        );

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) {
        Process.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = createPageVariablesMap(req);

                    login = req.getParameter("login");
                    pass = req.getParameter("pass");
                    email = req.getParameter("email");

                    try {
                        WriteToDB.writeUserToDB();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    pageVariables.put("login", login);
                    resp.getWriter().println(PageGenerator.instance().
                            getPage("authorizationAnswer.html", pageVariables));

                }
        );
    }

    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }
}
