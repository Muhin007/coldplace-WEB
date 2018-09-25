package com.github.muhin007.coldplaceweb.servlets;

import com.github.muhin007.coldplaceweb.data.AccountService;
import com.github.muhin007.coldplaceweb.data.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInServlet extends HttpServlet {
    public static String login;
    public static String pass;
    public static String email;
    public static String role;

    private final AccountService accountService;

    public SignInServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        login = request.getParameter("login");
        pass = request.getParameter("pass");
        email = request.getParameter("email");
        role = request.getParameter("role");

        if (login == null || pass == null || email == null) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile userProfile = accountService.getUserByLogin(login);
        if (userProfile == null || !userProfile.getPass().equals(pass)) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Unauthorized");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String sessionId = request.getSession().getId();
        accountService.addSession(sessionId, userProfile);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Authorized");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
