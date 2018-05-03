package com.github.muhin007.coldplaceweb.Servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;



public class ColdplaceServletButton extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter writer = response.getWriter();
        String title = "ColdplaceButton";
        String docType = "<!DOCTYPE html>";
        writer.println(docType + "<html><head><title>" + title + "</title></head><body>");
        writer.println("</head><body><p><button>PRESS</button></p>");
        writer.println("</body></html>");

    }
}
