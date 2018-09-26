package com.github.muhin007.coldplaceweb.servlets;

import com.github.muhin007.coldplaceweb.PageGenerator;
import com.github.muhin007.coldplaceweb.Process;
import com.github.muhin007.coldplaceweb.data.AccountService;
import com.github.muhin007.coldplaceweb.data.UserProfile;
import com.github.muhin007.coldplaceweb.data.WriteToDB;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
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

                    login = req.getParameter("login");
                    pass = req.getParameter("pass");
                    email = req.getParameter("email");
                    role = req.getParameter("role");

                    if (login == null || login.trim().isEmpty() || pass == null || pass.trim().isEmpty()) {
                        Map<String, Object> pageVariables = createPageVariablesMap(req);
                        pageVariables.put("message", "Вы ввели не все данные в форму. Попробуйте еще раз.");
                        resp.getWriter().println(PageGenerator.instance().
                                getPage("repeatedRegistration.html", pageVariables));
                        return;
                    } else {
                        WriteToDB.writeUserProfileToDB();
                    }

                    UserProfile userProfile = accountService.getUserByLogin(login);
                    if (userProfile != null) {
                        response.setContentType("text/html;charset=utf-8");
                        response.getWriter().println("Этот логин уже занят, выберите другой.");
                        response.setStatus(HttpServletResponse.SC_OK);
                        return;
                    }

                    accountService.addNewUser(new UserProfile(login, pass, email, role));
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().println("Регистрация успешно пройдена успешно.");
                    response.setStatus(HttpServletResponse.SC_OK);
                }
        );
    }


    private static Map<String, Object> createPageVariablesMap(HttpServletRequest request) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("parameters", request.getParameterMap().toString());
        return pageVariables;
    }
}
