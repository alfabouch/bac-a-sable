package fr.gouv.rie.e2.application.bacasable.persistance;

import fr.gouv.rie.e2.application.bacasable.metier.Agent;
import fr.gouv.rie.e2.application.commun.persistance.AbstractDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.github.javafaker.Faker;

public class AgentService extends AbstractDatabase<Agent> {
    
    public AgentService(Connection connection) {
        
        super(connection);
    }
    
    public static void main(String[] args) throws SQLException {
    
        AgentService agentService = new AgentService(DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/bacasable", "postgres", "postgres"));
        agentService.queryWithStatement("TRUNCATE TABLE bacasable.agent CASCADE");
    
        Faker faker = new Faker();
        for (int id = 1; id <= 6; id++) {
            agentService.queryWithStatement("INSERT INTO bacasable.agent VALUES (" + id + ",'" + faker.superhero().prefix() + "','" + faker.superhero().name() + "');");
        }
        List<Agent> agents = agentService.queryList(agentService.query().all());
        System.out.println(agents.size());
    }
}
