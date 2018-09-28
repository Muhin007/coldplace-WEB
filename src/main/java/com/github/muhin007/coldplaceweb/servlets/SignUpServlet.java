package com.github.muhin007.coldplaceweb.servlets;

import com.github.muhin007.coldplaceweb.PageGenerator;
import com.github.muhin007.coldplaceweb.Process;
import com.github.muhin007.coldplaceweb.data.AccountService;
import com.github.muhin007.coldplaceweb.data.ReadDB;
import com.github.muhin007.coldplaceweb.data.UserProfile;
import com.github.muhin007.coldplaceweb.data.WriteToDB;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignUpServlet extends HttpServlet {
    public static String login;
    public static String pass;
    public static String email;
    public static String role;

    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {

        Process.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {
                    Map<String, Object> pageVariables = createPageVariablesMap(req);
                    pageVariables.put("login", "");
                    pageVariables.put("pass", "");
                    pageVariables.put("email", "");
                    pageVariables.put("role", "");
                    resp.getWriter().println(PageGenerator.instance().
                            getPage("signUp.html", pageVariables));
                }
        );
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        Process.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {

                    Map<String, Object> pageVariables = createPageVariablesMap(req);

                    login = req.getParameter("login");
                    pass = req.getParameter("pass");
                    email = req.getParameter("email");
                    role = req.getParameter("role");

                    if (login == null || login.trim().isEmpty() || pass == null || pass.trim().isEmpty()) {

                        pageVariables.put("message", "Вы ввели не все данные в форму. Попробуйте еще раз.");
                        resp.getWriter().println(PageGenerator.instance().
                                getPage("repeatedSignUp.html", pageVariables));
                        return;
                    } else {
                        List<UserProfile> users = ReadDB.readUserFromDB();
                        UserProfile foundedUser = null;
                        for (UserProfile user : users) {
                            if (login.equals(user.getLogin())) {
                                foundedUser = user;
                                break;
                            }
                        }

                        if (foundedUser != null) {
                            pageVariables.put("message", "Этот логин уже занят, выберите другой.");
                            resp.getWriter().println(PageGenerator.instance().
                                    getPage("repeatedSignUp.html", pageVariables));

                        } else {
                            WriteToDB.writeUserProfileToDB();

                            pageVariables.put("message", "Спасибо за регистрацию.");
                            resp.getWriter().println(PageGenerator.instance().
                                    getPage("registrationAnswer.html", pageVariables));
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
