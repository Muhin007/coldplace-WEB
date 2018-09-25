package com.github.muhin007.coldplaceweb;


import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

public class Process {

    private static final Logger log = Logger.getLogger(Process.class);

    public static void process(HttpServletRequest request,
                               HttpServletResponse response,
                               Action action) {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");

            action.action(request, response);

            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            log.error("Произошла ошибка. Подробности смотри в log-файле", e);

            PrintWriter writer;
            try {
                writer = response.getWriter();
            } catch (IOException ex) {
                log.error("", ex);
                return;
            }

            writer.println(PageGenerator.instance().getPage("exception500.html", new HashMap<>()));
        }

    }

}