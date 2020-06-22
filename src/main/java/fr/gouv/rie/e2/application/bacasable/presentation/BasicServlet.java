package fr.gouv.rie.e2.application.bacasable.presentation;

import fr.gouv.rie.e2.application.bacasable.persistance.AgentService;

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
    
        AgentService agentService = new AgentService();
    
        //        ((DataSource)((Context)(new InitialContext().lookup("java:/comp/env"))).lookup("jdbc/bacasable")).getConnection();
    
        request.setAttribute("agents", agentService.queryList(agentService.query().all()));
    
        ctx.getRequestDispatcher("/accueil.jsp").forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
    }
    
    public void destroy() {
    }
    
    @Override
    public void init() {
        
        ctx = getServletContext();
    }
}