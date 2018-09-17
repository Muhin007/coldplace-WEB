package com.github.muhin007.coldplaceweb;



import com.github.muhin007.coldplaceweb.data.AccountService;
import com.github.muhin007.coldplaceweb.servlets.*;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Main {
    public static void main(String[] args) throws Exception {

        AccountService accountService = new AccountService();

        freemarker.log.Logger.selectLoggerLibrary(freemarker.log.Logger.LIBRARY_NONE);
        org.apache.log4j.PropertyConfigurator.configure("log4j.properties");

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new RegistrationServlet()), "/*");
        context.addServlet(new ServletHolder(new ButtonColdplaceServlet()), "/button");
        context.addServlet(new ServletHolder(new SearchCityColdplaceServlet()), "/search");
        context.addServlet(new ServletHolder(new URLColdplaceServlet()), "/URLRead");
        context.addServlet(new ServletHolder(new SessionsServlet(accountService)), "/session");

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






