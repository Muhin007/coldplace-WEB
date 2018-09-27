package com.github.muhin007.coldplaceweb.servlets;

import com.github.muhin007.coldplaceweb.PageGenerator;
import com.github.muhin007.coldplaceweb.Process;
import com.github.muhin007.coldplaceweb.data.ReadDB;
import com.github.muhin007.coldplaceweb.data.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignInServlet extends HttpServlet {
    public static String login;
    public static String pass;
    public static String email;
    public static String role;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {

        Process.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = createPageVariablesMap(req);
                    pageVariables.put("login", "");
                    pageVariables.put("pass", "");
                    resp.getWriter().println(PageGenerator.instance().
                            getPage("signIn.html", pageVariables));
                }
        );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Process.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {
                    login = req.getParameter("login");
                    pass = req.getParameter("pass");

                    if (login == null || pass == null) {
                        Map<String, Object> pageVariables = createPageVariablesMap(req);
                        pageVariables.put("message", "Вы ввели не все данные в форму. Попробуйте еще раз.");
                        resp.getWriter().println(PageGenerator.instance().
                                getPage("noSignInData.html", pageVariables));
                        return;
                    } else {

                        List<UserProfile> users = null;
                        try {
                            users = ReadDB.readUserFromDB();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        UserProfile foundedUser = null;
                        for (UserProfile user : users) {
                            if (login.equals(user.getLogin())) {
                                break;
                            }
                            if (foundedUser != null) {
                                Map<String, Object> pageVariables = createPageVariablesMap(req);
                                pageVariables.put("message", "Пользователь с такими данными уже зарегистрирован.");
                                resp.getWriter().println(PageGenerator.instance().
                                        getPage("repeatedRegistration.html", pageVariables));
                            }
                        }
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
