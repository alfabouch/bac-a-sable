package fr.gouv.rie.e2.application.bacasable.persistance;

import fr.gouv.rie.e2.application.bacasable.metier.Projet;
import fr.gouv.rie.e2.application.commun.persistance.AbstractDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProjetService extends AbstractDatabase<Projet> {
    
    public ProjetService(Connection connection) {
        
        super(connection);
    }
    
    @Override
    public Projet getEntity(ResultSet resultSet) throws SQLException {
        
        Projet projet = super.getEntity(resultSet);
        
        AgentService agentService = new AgentService(connection);
        
        projet.setMoa(agentService.queryOne(agentService.query().where("id = " + resultSet.getLong("moa"))));
        projet.setAmoa(agentService.queryOne(agentService.query().where("id = " + resultSet.getLong("amoa"))));
        
        return projet;
    }
    
    public static void main(String[] args) throws SQLException {
        
        ProjetService projetService = new ProjetService(DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/bacasable", "postgres", "postgres"));
        List<Projet>  projets       = projetService.queryList(projetService.query().all());
        System.out.println("projets = " + projets);
    }
}