package fr.gouv.rie.e2.application.bacasable.presentation;

import fr.gouv.rie.e2.application.bacasable.metier.Projet;
import fr.gouv.rie.e2.application.bacasable.persistance.AgentService;
import fr.gouv.rie.e2.application.bacasable.persistance.ProjetService;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class BasicServlet extends HttpServlet {
    
    private ServletContext ctx = null;
    private Connection     connection;
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        
        AgentService agentService = new AgentService(connection);
        request.setAttribute("agents", agentService.queryList(agentService.query().all()));
        ProjetService projetService = new ProjetService(connection);
        List<Projet>  projets       = projetService.queryList(projetService.query().all());
        request.setAttribute("projets", projets);
        
        ctx.getRequestDispatcher("/accueil.jsp").forward(request, response);
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
    
    }
    
    public void destroy() {
    
    }
    
    @Override
    public void init() {
    
        ctx = getServletContext();
        try {
            connection = ((DataSource) ((Context) (new InitialContext().lookup("java:/comp/env"))).lookup("jdbc/bacasable")).getConnection();
        }
        catch (SQLException throwables) {
            System.out.println("Erreur d'initialisation de la base de donnée.");
            throwables.printStackTrace();
        }
        catch (NamingException e) {
            System.out.println("Erreur dans le 'lookup naming' de ma base de donnée.");
            e.printStackTrace();
        }
    }
}