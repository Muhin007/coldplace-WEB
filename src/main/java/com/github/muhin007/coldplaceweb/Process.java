package com.github.muhin007.coldplaceweb;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Process {
    public static void process(HttpServletRequest request,
                               HttpServletResponse response, Action action) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        try {
            action.action(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

}