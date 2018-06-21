package com.github.muhin007.coldplaceweb;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Process {
    public static void process(HttpServletRequest request,
                               HttpServletResponse response, Action action) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");

        action.action(request, response);

        response.setStatus(HttpServletResponse.SC_OK);
    }
    public interface Action {
        void action(HttpServletRequest request, HttpServletResponse response)throws IOException;

    }
}