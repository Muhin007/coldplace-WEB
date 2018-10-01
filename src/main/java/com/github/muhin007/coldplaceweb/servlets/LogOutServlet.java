package com.github.muhin007.coldplaceweb.servlets;

import com.github.muhin007.coldplaceweb.PageGenerator;
import com.github.muhin007.coldplaceweb.Process;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class LogOutServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Process.process(request, response, (HttpServletRequest req, HttpServletResponse resp) -> {

            Map<String, Object> pageVariables = new HashMap<>();

                    HttpSession session = req.getSession(false);

                    if (session != null) {
                        session.invalidate();
                        pageVariables.put("message", "Вы вышли из системы");
                        resp.getWriter().println(PageGenerator.instance().
                                getPage("repeatedSignIn.html", pageVariables));
                        System.out.println("Пользователь вышел из системы");
                    }
                }
        );
    }
}