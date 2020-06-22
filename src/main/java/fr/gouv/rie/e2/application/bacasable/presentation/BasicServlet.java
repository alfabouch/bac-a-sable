package fr.gouv.rie.e2.application.bacasable.presentation;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BasicServlet extends HttpServlet {
    
    ServletContext ctx = null;
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
        ctx.getRequestDispatcher("/accueil.jsp").forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
    
    }
    
    public void destroy() {
        
        System.out.println("Servlet is being destroyed");
    }
    
    @Override
    public void init() {
        
        ctx = getServletContext();
    }
}