package com.github.muhin007.coldplaceweb;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class Main {
    public static void main(String[] args) throws Exception {
        ColdplaceServlet coldplaceServlet = new ColdplaceServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(coldplaceServlet), "/coldplaceweb");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
        System.out.println("Сервер запущен");
        server.join();
    }
}