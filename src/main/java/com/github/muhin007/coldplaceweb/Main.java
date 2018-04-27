package com.github.muhin007.coldplaceweb;

import com.github.muhin007.coldplaceweb.Servlets.ColdplaceServletButton;
import com.github.muhin007.coldplaceweb.Servlets.ColdplaceServletSearchCity;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {
        ColdplaceServletButton coldplaceServletButton = new ColdplaceServletButton();
        ColdplaceServletSearchCity coldplaceServletSearchCity = new ColdplaceServletSearchCity();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(coldplaceServletButton), "/button");
        context.addServlet(new ServletHolder(coldplaceServletSearchCity), "/*");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        System.out.println("Сервер запущен");
        server.join();
    }
}





