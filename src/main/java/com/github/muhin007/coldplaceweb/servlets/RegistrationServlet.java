package com.github.muhin007.coldplaceweb.servlets;

import com.github.muhin007.coldplaceweb.PageGenerator;
import com.github.muhin007.coldplaceweb.Process;
import com.github.muhin007.coldplaceweb.data.ReadDB;
import com.github.muhin007.coldplaceweb.data.User;
import com.github.muhin007.coldplaceweb.data.WriteToDB;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrationServlet extends HttpServlet {

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
                    pageVariables.put("email", "");
                    pageVariables.put("role", "");
                    resp.getWriter().println(PageGenerator.instance().
                            getPage("registration.html", pageVariables));
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
                    role = req.getParameter("role");

                    List<User> users = ReadDB.readUserFromDB();
                    User foundedUser = null;
                    User foundedEmail = null;
                    for (User user : users) {
                        if (login.equals(user.getLogin())) {
                            foundedUser = user;
                            break;
                        }
                    }
                    for (User email : users) {
                        if (email.equals(email.getEmail())) {
                            foundedEmail = email;
                            break;
                        }
                    }
                    if (foundedUser != null || foundedEmail != null) {
                        pageVariables.put("message", "Пользователь с такими данными уже зарегистрирован. " +
                                "Если это вы, то войдите в систему под своим логином. Если это не вы то " +
                                "придумайте другой логин или войдите в систему.");
                        resp.getWriter().println(PageGenerator.instance().
                                getPage("repeatedRegistration.html", pageVariables));

                    } else {
                        WriteToDB.writeUserToDB();

                        pageVariables.put("message", "Вы зарегистировались в системе как " + login +
                                " и будете перенаправлены на страницу приложения.");
                        resp.getWriter().println(PageGenerator.instance().
                                getPage("registrationAnswer.html", pageVariables));
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

