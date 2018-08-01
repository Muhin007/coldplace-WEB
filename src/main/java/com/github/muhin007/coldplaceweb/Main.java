package com.github.muhin007.coldplaceweb;


import com.github.muhin007.coldplaceweb.Servlets.ButtonColdplaceServlet;
import com.github.muhin007.coldplaceweb.Servlets.SearchCityColdplaceServlet;
import com.github.muhin007.coldplaceweb.Servlets.URLColdplaceServlet;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {


        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new ButtonColdplaceServlet()), "/button");
        context.addServlet(new ServletHolder(new SearchCityColdplaceServlet()), "/*");
        context.addServlet(new ServletHolder(new URLColdplaceServlet()), "/URLRead");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setResourceBase("templates");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});

        Server server = new Server(8080);
        server.setHandler(handlers);

        server.start();
        System.out.println("Сервер запущен http://localhost:8080");
        server.join();
    }
}





