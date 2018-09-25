package com.github.muhin007.coldplaceweb.servlets;

import com.github.muhin007.coldplaceweb.data.AccountService;
import com.github.muhin007.coldplaceweb.data.UserProfile;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpServlet extends HttpServlet {

    private final AccountService accountService;

    public SignUpServlet(AccountService accountService) {
        this.accountService = accountService;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        if (login == null || login.trim().isEmpty() || pass == null || pass.trim().isEmpty()) {
            response.setContentType("text/html;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        UserProfile userProfile = accountService.getUserByLogin(login);
        if (userProfile != null) {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("This login already exists");
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        accountService.addNewUser(new UserProfile(login, pass));
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Success registered");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
