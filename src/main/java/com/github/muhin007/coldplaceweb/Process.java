package com.github.muhin007.coldplaceweb;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Process {

    public static void process(HttpServletRequest request, HttpServletResponse response, HttpServletRequest req,
                               HttpServletResponse resp) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        action(request, response);

        response.setStatus(HttpServletResponse.SC_OK);
    }

    private static void action(HttpServletRequest req, HttpServletResponse resp) {
    }
}
