package fr.gouv.rie.e2.application.bacasable.persistance;

import fr.gouv.rie.e2.application.bacasable.metier.Agent;
import fr.gouv.rie.e2.application.commun.persistance.AbstractDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class AgentService extends AbstractDatabase<Agent> {
    
    public AgentService(Connection connection) {
        
        super(connection);
    }
    
    public static void main(String[] args) throws SQLException {
        
        AgentService agentService = new AgentService(DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/bacasable", "postgres", "postgres"));
        List<Agent>  agents       = agentService.queryList(agentService.query().all());
        System.out.println("agents = " + agents);
    }
}
