package com.github.muhin007.coldplaceweb;

import com.github.muhin007.coldplaceweb.Servlets.ButtonColdplaceServlet;
import com.github.muhin007.coldplaceweb.Servlets.ColdplaceServletSearchCity;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
       
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new ButtonColdplaceServlet()), "/button");
        context.addServlet(new ServletHolder(new ColdplaceServletSearchCity()), "/*");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        System.out.println("Сервер запущен");
        server.join();
    }
}





