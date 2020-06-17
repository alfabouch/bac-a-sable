package fr.gouv.rie.e2.application.bacasable.persistance;

import fr.gouv.rie.e2.application.bacasable.metier.Agent;
import fr.gouv.rie.e2.application.commun.persistance.AbstractDatabase;

import java.util.List;

public class AgentService extends AbstractDatabase<Agent> {
    
    public static void main(String[] args) {
        
        AgentService agentService = new AgentService();
        List<Agent>  agents       = agentService.queryList("SELECT * FROM bacasable.agent");
        System.out.println("agents = " + agents);
    }
}
