package com.github.muhin007.coldplaceweb;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.log4j.Logger;

public class Process {

    private static final Logger log = Logger.getLogger(Process.class);

    public static void process(HttpServletRequest request,
                               HttpServletResponse response, Action action) throws IOException {
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=utf-8");

            try {
                action.action(request, response);
            } catch (SQLException e) {
                log.error("Проверьте подключение к БД", e);
            }

            response.setStatus(HttpServletResponse.SC_OK);
        }

        catch (NullPointerException e) {
            log.error("Нет подключения к БД. Проверьте адрес," +
                    " имя пользователя и пароль", e);
        }

    }
}